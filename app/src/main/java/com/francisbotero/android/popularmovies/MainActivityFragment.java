package com.francisbotero.android.popularmovies;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class MainActivityFragment extends Fragment {
    MovieRepository movieRepository;
    ImageAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        GridView gridview = (GridView)rootView.findViewById(R.id.gridview_movies);
        adapter = new ImageAdapter(getActivity());
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new MoviePosterClickListener());
        movieRepository = new TheMovieDbMovieRepository(new PreferenceRepository(getActivity()));
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        FetchMovieDataTask task = new FetchMovieDataTask(adapter, movieRepository);
        task.execute();
    }
}
