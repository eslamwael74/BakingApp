package com.inq.eslamwael74.bakingapp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.inq.eslamwael74.bakingapp.Model.Recipe;

/**
 * Created by eslam on 11/15/2017.
 */

public class BakingService extends IntentService {

    public static final String INGREDIENTS = "INGREDIENTS";
    public static final String ACTION_UPDATE_INGREDIENTS_WIDGET = "com.inq.eslamwael74.bakingapp.action.WIDGET_UPDATE";


    public BakingService(String name) {
        super(name);
    }


    public static void startActionService(Context context, Recipe recipe){
        Intent intent = new Intent(context,BakingService.class);
        intent.putExtra(INGREDIENTS,recipe);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            handleUpdateActionService((Recipe) intent.getExtras().get(INGREDIENTS));
        }
    }

    private void handleUpdateActionService(Recipe recipe){

        Intent intent = new Intent(ACTION_UPDATE_INGREDIENTS_WIDGET);
        intent.setAction(ACTION_UPDATE_INGREDIENTS_WIDGET);
        intent.putExtra(INGREDIENTS,recipe);
        sendBroadcast(intent);
    }
}
