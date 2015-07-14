package com.francisbotero.android.popularmovies;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

public class FetchMovieDataTask extends AsyncTask<String, Void, ParcelableMovie[]> {
    ArrayAdapter<ParcelableMovie> adapter;
    MovieRepository repository;
    Toaster toaster;

    public FetchMovieDataTask(ArrayAdapter<ParcelableMovie> adapter, MovieRepository repository, Toaster toaster) {
        this.adapter = adapter;
        this.repository = repository;
        this.toaster = toaster;
    }

    @Override
    protected ParcelableMovie[] doInBackground(String... params) {
        return repository.get();
    }

    @Override
    protected void onPostExecute(ParcelableMovie[] result) {
        if (result != null) {
            this.adapter.clear();
            this.adapter.addAll(result);
        } else {
            toaster.make("Sorry you appear to be offline. Try again later.");
        }
    }
}
