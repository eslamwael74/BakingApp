package com.inq.eslamwael74.bakingapp.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;

import com.inq.eslamwael74.bakingapp.Fragment.RecipeDetailsFragment;
import com.inq.eslamwael74.bakingapp.Fragment.RecipeFragment;
import com.inq.eslamwael74.bakingapp.R;
import com.inq.eslamwael74.bakingapp.UtilClass;

/**
 * Created by eslamwael74 on 02/11/17.
 */

public class RecipeActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        if(savedInstanceState == null){

            if(!UtilClass.isTablet(getApplicationContext())){

                Fragment listFragment = new RecipeFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame,listFragment);
                fragmentTransaction.commit();
            }
            else{

                Fragment listFragment = new RecipeFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame1,listFragment);
                fragmentTransaction.commit();

                Fragment DetailFragment = new RecipeDetailsFragment();
                FragmentManager detailManager = getSupportFragmentManager();
                FragmentTransaction DetailTransaction = detailManager.beginTransaction();
                DetailTransaction.add(R.id.frame2,DetailFragment);
                DetailTransaction.commit();

            }


        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
