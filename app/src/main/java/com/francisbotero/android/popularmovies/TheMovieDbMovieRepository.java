package com.francisbotero.android.popularmovies;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TheMovieDbMovieRepository implements MovieRepository {
    private PreferenceRepository preferenceRepository;

    public TheMovieDbMovieRepository(PreferenceRepository preferenceRepository)
    {
        this.preferenceRepository = preferenceRepository;
    }

    @Override
    public Movie[] get() {
        Uri.Builder builder = new Uri.Builder();
        String sortBy = preferenceRepository.getSort() + ".desc";
        builder.scheme("http")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter("sort_by", sortBy)
                .appendQueryParameter("api_key", "184ae5c90647d827f9429bd107ff7cc3");

        String string_url = builder.build().toString();
//        Log.v(LOG_TAG, "Using URL: " + string_url);
        try {
            URL url = new URL(string_url);
            JsonFetcher fetcher = new JsonFetcher();
            String data = fetcher.get(url);
            MovieDataParser parser = new MovieDataParser();
            Movie[] movies = parser.parse(data);
            return movies;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
