package com.example.news.apiUtilities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    String BASE_URL = "https://newsapi.org/v2/";
    @GET("top-headlines")
    Call<NewsArticles> getNews(@Query("country") String country,@Query("pageSize") String pagesize,@Query("category") String category,@Query("apiKey") String API_KEY);
}
