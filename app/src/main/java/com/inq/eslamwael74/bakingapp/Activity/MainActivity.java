package com.inq.eslamwael74.bakingapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.inq.eslamwael74.bakingapp.Adapter.MainAdapter;
import com.inq.eslamwael74.bakingapp.Model.Recipe;
import com.inq.eslamwael74.bakingapp.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.recylerview)
    RecyclerView recyclerView;

    ArrayList<Recipe> recipes;
    MainAdapter mainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getWebService();


    }

    public JSONArray getWebService(){


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json").build();
        try {
            Response response = client.newCall(request).execute();
            return new JSONArray(response.body().toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;


//        BakeRequest bakeRequest = new BakeRequest();
//
//        RetrofitWebService.getService().Bake().enqueue(new Callback<BakeResponse>() {
//            @Override
//            public void onResponse(Call<BakeResponse> call, Response<BakeResponse> response) {
//                recipes = response.body().getRecipes();
//                Log.d(TAG, "onResponse: " + recipes);
//                mainAdapter = new MainAdapter(MainActivity.this, recipes);
//                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
//                recyclerView.setLayoutManager(manager);
//                recyclerView.setAdapter(mainAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<BakeResponse> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Oopsssss", Toast.LENGTH_SHORT).show();
//            }
//        });


    }
}
