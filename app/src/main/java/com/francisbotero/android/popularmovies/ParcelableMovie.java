package com.francisbotero.android.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableMovie implements Parcelable, Movie {
    public String getSynopsis() {
        return synopsis;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public float getRating() {
        return rating;
    }


    private String title;
    private String posterUrl;
    private String releaseDate;
    private float rating;
    private String synopsis;




    public ParcelableMovie(Movie movie) {
        this.title = movie.getTitle();
        this.posterUrl = movie.getPosterUrl();
        this.releaseDate = movie.getReleaseDate();
        this.rating = movie.getRating();
        this.synopsis = movie.getSynopsis();
    }

    protected ParcelableMovie(Parcel in) {
        this.title = in.readString();
        this.posterUrl = in.readString();
        this.releaseDate = in.readString();
        this.rating = in.readFloat();
        this.synopsis = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(posterUrl);
        dest.writeString(releaseDate);
        dest.writeFloat(rating);
        dest.writeString(synopsis);
    }

    public static final Creator<ParcelableMovie> CREATOR = new Creator<ParcelableMovie>() {
        @Override
        public ParcelableMovie createFromParcel(Parcel in) {
            return new ParcelableMovie(in);
        }

        @Override
        public ParcelableMovie[] newArray(int size) {
            return new ParcelableMovie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
