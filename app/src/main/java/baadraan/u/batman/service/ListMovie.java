package baadraan.u.batman.service;

import com.google.gson.annotations.SerializedName;

public class ListMovie {

    @SerializedName("Title")
    private String Title;

    @SerializedName("Year")
    private String Year;

    @SerializedName("imdbID")
    private String imdbID;

    @SerializedName("Type")
    private String Type;

    @SerializedName("Poster")
    private String Poster;


    public String getTitle(){
        return Title;
    }

    public String getYear(){
        return Year;
    }

    public String getImdbID(){
        return imdbID;
    }

    public String getType(){
        return Type;
    }

    public String getPoster(){
        return Poster;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setYear(String year) {
        Year = year;
    }

}
