package com.inq.eslamwael74.bakingapp.WebService;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class is a singleton.
 */
public class RetrofitWebService {
    private static final String TAG = RetrofitWebService.class.getSimpleName();
    private static final Map<String, RetrofitService> mServices = new HashMap<>();
//    public static final String url = "http://youcook.4art-studio.com/";
    public static final String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    private RetrofitWebService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mServices.put(url, retrofit.create(RetrofitService.class));
    }

    public static RetrofitService getService() {
        if (null == mServices.get(url)) {
            new RetrofitWebService();
        }
        return mServices.get(url);
    }


    public static void log(Throwable t) {
        Log.e(TAG, null != t.getMessage() ? t.getMessage() : t.toString());
    }
}
