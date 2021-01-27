package com.AsmaulHusna.tubes;

public class AsmaulHusna {
//    public int data;
    public String latin;
    public String arabic;
    public String translation_id;

    public AsmaulHusna (){
    }

    public AsmaulHusna(String latin, String arabic, String translation_id){
        this.latin=latin;
        this.arabic=arabic;
        this.translation_id=translation_id;
    }

    public String getLatin() {

        return latin;
    }

    public void setLatin(String latin) {

        this.latin = latin;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getTranslation_id() {
        return translation_id;
    }

    public void setTranslation_id(String translation_id) {
        this.translation_id = translation_id;
    }
}
