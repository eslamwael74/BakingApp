package com.inq.eslamwael74.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.inq.eslamwael74.bakingapp.Model.Ingredient;
import com.inq.eslamwael74.bakingapp.Model.Recipe;

/**
 * Created by Eslam Wael on 11/25/2017.
 */

public class ListViewWidgetService extends RemoteViewsService {

    Recipe recipe;
    static String RECIPE_KEY = "recipe";

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return null;
    }


    class ListRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

        Context context;

        public ListRemoteViewFactory(Context context) {
            this.context = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            recipe = BakingWidgetProvider.recipe;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return recipe.getIngredients().size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            //here i return my widget View...

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            Ingredient ingredient = recipe.getIngredients().get(position);
            remoteViews.setTextViewText(R.id.tv_widget_ingredients,
                    ingredient.getIngredient() + "\n" +
                            "Quantity: " + ingredient.getQuantity() + "\n" +
                            "Measure: " + ingredient.getMeasure() + "\n"

            );

            Intent intent = new Intent();
            intent.putExtra(RECIPE_KEY,recipe);
            remoteViews.setOnClickFillInIntent(R.id.tv_widget_ingredients,intent);

            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }


}
