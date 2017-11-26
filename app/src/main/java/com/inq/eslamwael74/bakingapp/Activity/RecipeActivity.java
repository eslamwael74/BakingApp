package com.inq.eslamwael74.bakingapp.Activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.inq.eslamwael74.bakingapp.Fragment.RecipeDetailsFragment;
import com.inq.eslamwael74.bakingapp.Fragment.RecipeFragment;
import com.inq.eslamwael74.bakingapp.OnStepSelectionChangeListener;
import com.inq.eslamwael74.bakingapp.R;
import com.inq.eslamwael74.bakingapp.UtilClass;

public class RecipeActivity extends AppCompatActivity implements OnStepSelectionChangeListener {

    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tap);


        if (savedInstanceState == null){

            if (!UtilClass.isTablet(this)){
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
    public void OnSelectionChanged(int position) {

    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(context, "I am RecipeActivity", Toast.LENGTH_LONG).show();
    }
}
