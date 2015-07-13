package com.francisbotero.android.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends ArrayAdapter<Movie> {
    public ImageAdapter(Context context) {
        super(context, 0);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_main_tile, parent, false);
        }
        ImageView button = (ImageView)convertView.findViewById(R.id.movie_tile);
        String posterUrl = getItem(position).getPosterUrl();
        button.setMinimumHeight(300);
        Picasso.with(getContext()).load(posterUrl).into(button);
        return convertView;
    }
}
