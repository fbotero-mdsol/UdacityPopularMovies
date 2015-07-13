package com.francisbotero.android.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceRepository {
    Context context;
    SharedPreferences preferences;
    public PreferenceRepository(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getSort() {
        String preferenceKey = context.getString(R.string.pref_sort_key);
        String defaultValue = context.getString(R.string.pref_sort_label_popularity);
        return preferences.getString(preferenceKey, defaultValue);
    }
}
