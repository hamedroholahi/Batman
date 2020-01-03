package baadraan.u.batman.service;

import com.google.gson.annotations.SerializedName;

public class MovieSingle extends ListMovie {

    @SerializedName("Rated")
    private String Rated;

    @SerializedName("Released")
    private String Released;

    @SerializedName("Runtime")
    private String Runtime;

    @SerializedName("Genre")
    private String Genre;

    @SerializedName("Director")
    private String Director;

    @SerializedName("Writer")
    private String Writer;

    @SerializedName("Actors")
    private String Actors;

    @SerializedName("Plot")
    private String Plot;

    @SerializedName("Language")
    private String Language;

    @SerializedName("Country")
    private String Country;

    @SerializedName("Awards")
    private String Awards;

    @SerializedName("Metascore")
    private String Metascore;

    @SerializedName("imdbRating")
    private String imdbRating;

    @SerializedName("imdbVotes")
    private String imdbVotes;

    @SerializedName("BoxOffice")
    private String BoxOffice;

    @SerializedName("Production")
    private String Production;

    @SerializedName("Response")
    private boolean Response;

    public void setRated(String rated) {
        Rated = rated;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public void setMetascore(String metascore) {
        Metascore = metascore;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public void setBoxOffice(String boxOffice) {
        BoxOffice = boxOffice;
    }

    public void setProduction(String production) {
        Production = production;
    }

    public void setResponse(boolean response) {
        Response = response;
    }

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getWriter() {
        return Writer;
    }

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCountry() {
        return Country;
    }

    public String getAwards() {
        return Awards;
    }

    public String getMetascore() {
        return Metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public String getProduction() {
        return Production;
    }

    public boolean isResponse() {
        return Response;
    }
}
