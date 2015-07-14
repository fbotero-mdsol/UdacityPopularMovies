package com.francisbotero.android.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.util.ArrayList;

/**
 * Created by Frankie on 7/13/2015.
 */
public class CoordinatedMovieRepository implements MovieRepository {
    ArrayList<MovieRepository> repositories;
    MovieRepository movieDbRepository;

    ParcelableMovie[] movies;

    public CoordinatedMovieRepository(Context context, Bundle savedInstanceState) {
        NetworkConnectivityChecker networkChecker = new NetworkConnectivityChecker(context);
        PreferenceRepository preferenceRepository = new PreferenceRepository(context);
        movieDbRepository = new TheMovieDbMovieRepository(preferenceRepository, networkChecker);
        repositories = new ArrayList<>();
        repositories.add(new SavedInstanceStateMovieRepository(savedInstanceState));
        repositories.add(movieDbRepository);
        movies = null;
    }

    @Override
    public ParcelableMovie[] get() {
        if (movies == null) {
            movies = loadData();
        }
        return movies;
    }

    private ParcelableMovie[] loadData() {
        ParcelableMovie[] movies = null;
        for(MovieRepository repository : repositories) {
            movies = repository.get();
            if (movies != null) {
                break;
            }
        }

        return movies;
    }
}
