package com.AsmaulHusna.tubes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AsmaulHusnaAdapter extends RecyclerView.Adapter<AsmaulHusnaAdapter.ViewHolder>{
    private Context context;
    private List <AsmaulHusna> list;

    public AsmaulHusnaAdapter(Context context, List<AsmaulHusna> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AsmaulHusnaAdapter.ViewHolder holder, int position) {
        AsmaulHusna asmaulHusna = list.get(position);

        holder.textLatin.setText (asmaulHusna.getLatin());
        holder.textArabic.setText (asmaulHusna.getArabic());
        holder.textTranslation_id.setText (asmaulHusna.getTranslation_id());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textLatin, textArabic, textTranslation_id;

        public ViewHolder(View itemView){
            super(itemView);
            textLatin = itemView.findViewById(R.id.main_latin);
            textArabic = itemView.findViewById(R.id.main_arabic);
            textTranslation_id = itemView.findViewById(R.id.main_translation);
        }
    }
}
