package com.inq.eslamwael74.bakingapp.Activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.inq.eslamwael74.bakingapp.Fragment.RecipeDetailsFragment;
import com.inq.eslamwael74.bakingapp.Model.Recipe;
import com.inq.eslamwael74.bakingapp.Model.Step;
import com.inq.eslamwael74.bakingapp.setOnStepSelectListener;

import com.inq.eslamwael74.bakingapp.Fragment.RecipeFragment;
import com.inq.eslamwael74.bakingapp.R;
import com.inq.eslamwael74.bakingapp.UtilClass;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity implements setOnStepSelectListener {

    Context context = this;

    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tap);

        recipe = getIntent().getExtras().getParcelable("recipe");

        if (savedInstanceState == null) {

            if (!UtilClass.isTablet(this)) {
                Fragment listFragment = new RecipeFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, listFragment);
                fragmentTransaction.commit();
            } else {
                RecipeFragment listFragment = RecipeFragment.newInstance(recipe);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame1, listFragment);
                fragmentTransaction.commit();

                Fragment DetailFragment = RecipeDetailsFragment.newInstance(recipe.getSteps(),0);
                FragmentManager detailManager = getSupportFragmentManager();
                FragmentTransaction DetailTransaction = detailManager.beginTransaction();
                DetailTransaction.replace(R.id.frame2, DetailFragment);
                DetailTransaction.commit();


            }


        }


    }

    @Override
    public void onStepSelect(ArrayList<Step> steps, int id) {

        Step step;

        for (int i = 0; i < steps.size(); i++) {

            if (id == steps.get(i).getId()) {

                step = new Step(id, steps.get(i).getShortDescription(), steps.get(i).getDescription(), steps.get(i).getVideoURL(), steps.get(i).getThumbnailURL());

                Toast.makeText(context, "" + id + step.getShortDescription(), Toast.LENGTH_SHORT).show();


            }
        }
        Fragment DetailFragment = RecipeDetailsFragment.newInstance(recipe.getSteps(),id);
        FragmentManager detailManager = getSupportFragmentManager();
        FragmentTransaction DetailTransaction = detailManager.beginTransaction();
        DetailTransaction.replace(R.id.frame2, DetailFragment);
        DetailTransaction.commit();

    }
}
