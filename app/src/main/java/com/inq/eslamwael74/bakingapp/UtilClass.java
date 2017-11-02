package com.inq.eslamwael74.bakingapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.widget.Toast;

/**
 * Created by eslamwael74 on 02/11/17.
 */

public class UtilClass {

    private static UtilClass utilClass;


    public static UtilClass getUtilClass() {
        return utilClass;
    }

    public static boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        boolean large = ((context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static void onFail(Context context) {
//        closeprogress();
        if (isNetworkConnected(context)) {
            Toast.makeText(context, "please try again later", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "check your network!", Toast.LENGTH_LONG).show();
        }
    }
}
