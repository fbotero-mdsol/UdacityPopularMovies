package com.francisbotero.android.popularmovies;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Frankie on 7/13/2015.
 */
public class Toaster {
    private Context context;
    private Toast previousToast;
    public Toaster(Context context) {
        this.context = context;
        previousToast = null;
    }

    public void make(String message) {
        if (previousToast != null) {
            previousToast.cancel();
        }
        previousToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        previousToast.show();
    }
}
