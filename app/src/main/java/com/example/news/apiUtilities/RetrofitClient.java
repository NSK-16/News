package com.example.news.apiUtilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient{

    private static RetrofitClient instance = null;
    private NewsApi myApi;

    private RetrofitClient()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(NewsApi.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        myApi = retrofit.create(NewsApi.class);
    }

    public static synchronized RetrofitClient getInstance()
    {
        if(instance == null)
            instance = new RetrofitClient();

        return instance;
    }
    public NewsApi getMyApi() {
        return myApi;
    }
}
