package com.francisbotero.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

public class MoviePosterClickListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Context context = view.getContext();
        Intent detailIntent = new Intent(context, MovieDetailActivity.class);
        Movie movieAtPosition = (Movie)parent.getItemAtPosition(position);
        ParcelableMovie movie = new ParcelableMovie(movieAtPosition);
        detailIntent.putExtra("Movie", movie);
        context.startActivity(detailIntent);
    }
}
