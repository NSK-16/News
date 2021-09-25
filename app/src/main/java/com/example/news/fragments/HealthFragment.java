package com.example.news.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.adapters.NewsAdapter;
import com.example.news.apiUtilities.NewsArticles;
import com.example.news.apiUtilities.NewsModelClass;
import com.example.news.apiUtilities.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthFragment extends Fragment {

    private List<NewsModelClass> healthNews = new ArrayList<>();
    String country = "in";
    String category = "health";
    int pageSize = 100;
    private RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    final String API_KEY = "ba88d060a3e049ca9fa46f2bea0d52c4";

    public HealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_health, container, false);
        recyclerView = v.findViewById(R.id.rvHealth);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext(),healthNews);
        recyclerView.setAdapter(newsAdapter);
        fetchNews();
        return v;
    }

    private void fetchNews()
    {
        Call<NewsArticles> call = RetrofitClient.getInstance().getMyApi().getNews(country,pageSize,category,API_KEY);
        call.enqueue(new Callback<NewsArticles>() {
            @Override
            public void onResponse(Call<NewsArticles> call, Response<NewsArticles> response) {
                if(response.isSuccessful()) {
                    if (!healthNews.isEmpty()) {
                        healthNews.clear();
                    }
                    healthNews=response.body().getArticles();
                    newsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NewsArticles> call, Throwable t) {
                Toast.makeText(getContext(),"Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });


    }
}