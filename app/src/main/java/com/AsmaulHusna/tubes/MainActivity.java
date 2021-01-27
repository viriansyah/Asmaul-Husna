package com.AsmaulHusna.tubes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity<JsonObject> extends AppCompatActivity {
    private String url = "https://islamic-api-zhirrr.vercel.app/api/asmaulhusna";

    private RecyclerView mList;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<AsmaulHusna> asmaulHusnaList;
    private RecyclerView.Adapter adapter;
    private MediaPlayer mediaPlayer;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Asmaul Husna");

        playsound();
        
        mList = findViewById(R.id.main_list);

        asmaulHusnaList =  new ArrayList<>();
        adapter = new AsmaulHusnaAdapter(getApplicationContext(),asmaulHusnaList);

        linearLayoutManager = new LinearLayoutManager(this);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        getData();
    }

    private void playsound() {
        try{
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        } catch (Exception e){

        }
        mediaPlayer = MediaPlayer.create(this, R.raw.nasyid);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    private void getData(){
        final ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                    try {
                        JSONArray jArr = response.getJSONArray("data");
                        for (int i = 0; i < jArr.length(); i++) {
                            JSONObject jsonObject = jArr.getJSONObject(i);
                            AsmaulHusna asmaulHusna = new AsmaulHusna();
                            asmaulHusna.setLatin(((JSONObject) jsonObject).getString("latin"));
                            asmaulHusna.setArabic(((JSONObject) jsonObject).getString("arabic"));
                            asmaulHusna.setTranslation_id(((JSONObject) jsonObject).getString("translation_id"));

                            asmaulHusnaList.add(asmaulHusna);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("volley", error.toString());
                        progressDialog.dismiss();
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(jsonObjectRequest);
            }
}
