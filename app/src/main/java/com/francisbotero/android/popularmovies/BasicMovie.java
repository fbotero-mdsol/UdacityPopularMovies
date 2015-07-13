package com.francisbotero.android.popularmovies;

public class BasicMovie implements Movie {
    private String title;
    private String posterUrl;
    private String yearReleased;
    private float rating;
    private String synopsis;


    public BasicMovie(String title, String posterUrl, String yearReleased, float rating, String synopsis) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.yearReleased = yearReleased;
        this.rating = rating;
        this.synopsis = synopsis;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getPosterUrl() {
        return posterUrl;
    }

    @Override
    public String getReleaseDate() {
        return yearReleased;
    }

    @Override
    public float getRating() {
        return rating;
    }

    @Override
    public String getSynopsis() {
        return synopsis;
    }
}
