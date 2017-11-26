package com.inq.eslamwael74.bakingapp.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.inq.eslamwael74.bakingapp.Adapter.MainAdapter;
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

    ArrayList<Recipe> recipes;
    MainAdapter mainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null){

            getWebService();

        }



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void getWebService() {

        RetrofitWebService.getService().Bake().enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, retrofit2.Response<ArrayList<Recipe>> response) {
                recipes = response.body();
                Log.d(TAG, "onResponse: " + recipes.size());
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

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(context, "I am MainActivity", Toast.LENGTH_LONG).show();
    }
}
