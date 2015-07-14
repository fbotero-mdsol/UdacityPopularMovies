package com.francisbotero.android.popularmovies;

import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by Frankie on 7/13/2015.
 */
public class SavedInstanceStateMovieRepository implements MovieRepository {
    public static String PARCELABLE_KEY = "movies";
    private Bundle savedInstanceState;

    public SavedInstanceStateMovieRepository(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    public ParcelableMovie[] get() {
        ParcelableMovie[] movies = null;
        if (savedInstanceState != null && savedInstanceState.containsKey(PARCELABLE_KEY)) {
            movies = (ParcelableMovie[])savedInstanceState.getParcelableArray(PARCELABLE_KEY);
        }
        return movies;
    }
}
