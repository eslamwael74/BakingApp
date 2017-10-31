package com.inq.eslamwael74.bakingapp.WebService;


import com.inq.eslamwael74.bakingapp.WebService.Response.BakeResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitService {

//    @Headers("Content-Type: application/json")
    @GET("baking.json")
    Call<BakeResponse> Bake();

}