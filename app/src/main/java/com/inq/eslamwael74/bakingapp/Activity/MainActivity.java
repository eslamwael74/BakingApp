package com.inq.eslamwael74.bakingapp.Activity;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.Toast;

import com.inq.eslamwael74.bakingapp.Adapter.MainAdapter;
import com.inq.eslamwael74.bakingapp.Model.Ingredient;
import com.inq.eslamwael74.bakingapp.Model.Recipe;
import com.inq.eslamwael74.bakingapp.R;
import com.inq.eslamwael74.bakingapp.UtilClass;
import com.inq.eslamwael74.bakingapp.WebService.RetrofitWebService;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Context context = this;


    @BindView(R.id.recylerview)
    RecyclerView recyclerView;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    ArrayList<Recipe> recipes;
    MainAdapter mainAdapter;

    Parcelable parcelableState;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null){

            getWebService();

        }
        else{
            recipes = (ArrayList<Recipe>) savedInstanceState.getSerializable("listRecipes");
            getRecipes(recipes);
            if (parcelableState != null) {
                recyclerView.getLayoutManager().onRestoreInstanceState(parcelableState);
            }
        }



    }

    public void getWebService() {

        RetrofitWebService.getService().Bake().enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, retrofit2.Response<ArrayList<Recipe>> response) {
                recipes = response.body();
                Log.d(TAG, "onResponse: " + recipes.size());

               getRecipes(recipes);

//                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
//                recyclerView.setLayoutManager(manager);
//                recyclerView.setAdapter(mainAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                UtilClass.onFail(context);
            }
        });

    }
    void getRecipes(ArrayList<Recipe> recipes){
        mainAdapter = new MainAdapter(MainActivity.this, recipes);

        if (!UtilClass.getUtilClass().isTablet(MainActivity.this)) {
            LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(mainAdapter);
        } else {
            GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 3);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(mainAdapter);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("listRecipes",  recipes);

        outState.putParcelable("recipe_position", recyclerView.getLayoutManager().onSaveInstanceState());

        outState.putIntArray("SCROLL_POSITION",
                new int[]{scrollView.getScrollX(), scrollView.getScrollY()});

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {

            parcelableState = savedInstanceState.getParcelable("recipe_position");


            int[] position = savedInstanceState.getIntArray("SCROLL_POSITION");

            if (position != null)
                scrollView.post(() -> scrollView.scrollTo(position[0], position[1]));


        }
    }


}
