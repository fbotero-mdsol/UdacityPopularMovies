package com.francisbotero.android.popularmovies;

import android.widget.ArrayAdapter;

/**
 * Created by Frankie on 7/13/2015.
 */
public class FetchMovieDataExecutor {
    ArrayAdapter<ParcelableMovie> adapter;
    MovieRepository repository;
    Toaster toaster;

    public FetchMovieDataExecutor(ArrayAdapter<ParcelableMovie> adapter, MovieRepository repository, Toaster toaster) {
        this.adapter = adapter;
        this.repository = repository;
        this.toaster = toaster;
    }

    public void execute() {
        FetchMovieDataTask task = new FetchMovieDataTask(adapter, repository, toaster);
        task.execute();
    }
}
