package com.francisbotero.android.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieDataParser {
    public Movie[] parse(String jsonData) {
        final String OWM_LIST = "results";
        JSONObject forecastJson = null;
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            forecastJson = new JSONObject(jsonData);
            JSONArray resultArray = forecastJson.getJSONArray(OWM_LIST);
            for (int idx = 0; idx < resultArray.length(); idx++) {
                JSONObject movieData = resultArray.getJSONObject(idx);
                String poster_path = movieData.getString("poster_path");
                String poster_url = "http://image.tmdb.org/t/p/w185/" + poster_path;
                String title = movieData.getString("title");
                String synopsis = movieData.getString("overview");
                String date = movieData.getString("release_date").substring(0,4);
                float rating = (float)movieData.getDouble("vote_average");
                Movie movie = new BasicMovie(title, poster_url, date, rating, synopsis);
                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies.toArray(new Movie[movies.size()]);
    }
}
