package com.francisbotero.android.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivityFragment extends Fragment {
    ImageAdapter adapter;
    FetchMovieDataExecutor fetchExecutor;
    MovieRepository movieRepository;

    MovieRepository refreshMovieRepository;

    public MainActivityFragment() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movie_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            FetchMovieDataTask task = new FetchMovieDataTask(adapter, refreshMovieRepository, new Toaster(getActivity()));
            task.execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        int count = adapter.getCount();
        ArrayList<ParcelableMovie> movies = new ArrayList<>(count);
        for (int idx = 0; idx < count; idx++) {
            movies.add(adapter.getItem(idx));
        }
        ParcelableMovie[] movieArray = new ParcelableMovie[count];
        movies.toArray(movieArray);
        outState.putParcelableArray(SavedInstanceStateMovieRepository.PARCELABLE_KEY, movieArray);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        GridView gridview = (GridView)rootView.findViewById(R.id.gridview_movies);
        Context context = getActivity();
        adapter = new ImageAdapter(context);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new MoviePosterClickListener());
        NetworkConnectivityChecker networkChecker = new NetworkConnectivityChecker(context);
        PreferenceRepository preferenceRepository = new PreferenceRepository(context);
        refreshMovieRepository = new TheMovieDbMovieRepository(preferenceRepository, networkChecker);

        movieRepository = new CoordinatedMovieRepository(context, savedInstanceState);
        Toaster toaster = new Toaster(context);
        fetchExecutor = new FetchMovieDataExecutor(adapter, movieRepository, toaster);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchExecutor.execute();
    }
}
