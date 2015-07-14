package com.francisbotero.android.popularmovies;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

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
    private NetworkConnectivityChecker networkChecker;

    public TheMovieDbMovieRepository(PreferenceRepository preferenceRepository, NetworkConnectivityChecker networkChecker)
    {
        this.preferenceRepository = preferenceRepository;
        this.networkChecker = networkChecker;
    }

    @Override
    public ParcelableMovie[] get() {
        ParcelableMovie[] movies = null;
        if (networkChecker.isConnected()) {
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
            try {
                URL url = new URL(string_url);
                JsonFetcher fetcher = new JsonFetcher();
                String data = fetcher.get(url);
                MovieDataParser parser = new MovieDataParser();
                movies = parser.parse(data);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }
}
