package com.francisbotero.android.popularmovies;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivityFragment extends Fragment {

    public MovieDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra("Movie")) {
            ParcelableMovie movie = intent.getParcelableExtra("Movie");
            TextView movie_title = (TextView)rootView.findViewById(R.id.movie_title);
            movie_title.setText(movie.getTitle());
            ImageView movie_poster = (ImageView)rootView.findViewById(R.id.movie_poster);
            Picasso.with(getActivity()).load(movie.getPosterUrl()).into(movie_poster);
            TextView movie_year_released = (TextView)rootView.findViewById(R.id.movie_release_date);
            movie_year_released.setText(movie.getReleaseDate());
            TextView movie_rating = (TextView)rootView.findViewById(R.id.movie_rating);
            movie_rating.setText(movie.getRating() + "/10");
            TextView movie_synopsis = (TextView)rootView.findViewById(R.id.movie_synopsis);
            movie_synopsis.setText(movie.getSynopsis());
        }
        return rootView;
    }
}
