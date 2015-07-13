package com.francisbotero.android.popularmovies;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

public class FetchMovieDataTask extends AsyncTask<String, Void, Movie[]> {
    ArrayAdapter<Movie> adapter;
    MovieRepository repository;

    public FetchMovieDataTask(ArrayAdapter<Movie> adapter, MovieRepository repository) {
        this.adapter = adapter;
        this.repository = repository;
    }

    @Override
    protected Movie[] doInBackground(String... params) {
        return repository.get();
    }

    @Override
    protected void onPostExecute(Movie[] result) {
        if (result != null) {
            this.adapter.clear();
            this.adapter.addAll(result);
        }
    }
}
