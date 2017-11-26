package com.inq.eslamwael74.bakingapp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.inq.eslamwael74.bakingapp.Model.Recipe;

/**
 * Created by Eslam Wael on 11/25/2017.
 */

public class BakingService extends IntentService {

    static final String INGREDIENTS_ACTIVITY = "INGREDIENTS_ACTIVITY";
    static final String ACTION_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public BakingService(String name) {
        super(name);
    }

    public static void updateService(Recipe recipe, Context context) {

        Intent intent = new Intent(context, BakingService.class);
        intent.putExtra(INGREDIENTS_ACTIVITY, recipe);
        context.startActivity(intent);

    }

    void handleServiceIntent(Recipe recipe) {

        Intent intent = new Intent(ACTION_UPDATE);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(INGREDIENTS_ACTIVITY,recipe);
        sendBroadcast(intent);

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null)
            handleServiceIntent((Recipe) intent.getExtras().get(INGREDIENTS_ACTIVITY));

    }
}
