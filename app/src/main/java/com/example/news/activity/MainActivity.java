package com.example.news.activity;

//TODO: * Add the individual fragments,viewpager and tab layout
//      * Beautify the app and support dark mode

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.news.NewsAdapter;
import com.example.news.apiUtilities.NewsArticles;
import com.example.news.apiUtilities.NewsModelClass;
import com.example.news.R;
import com.example.news.apiUtilities.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsAdapter.NewsItemClickListener {
    List<NewsModelClass> mNews = new ArrayList<>();
    NewsAdapter mNewsAdapter;
    RecyclerView recyclerView;

    String API_KEY = "ba88d060a3e049ca9fa46f2bea0d52c4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvNewsArticles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchNews();


    }

    private void fetchNews() {
        Call<NewsArticles> call = RetrofitClient.getInstance().getMyApi().getNews("in",API_KEY);
        call.enqueue(new Callback<NewsArticles>() {
            @Override
            public void onResponse(Call<NewsArticles> call, Response<NewsArticles> response) {
                if(response.isSuccessful()) {
                    if (!mNews.isEmpty()) {
                        mNews.clear();
                    }
                    mNews=response.body().getArticles();
                    mNewsAdapter = new NewsAdapter(MainActivity.this::onNewsClick,mNews);
                    recyclerView.setAdapter(mNewsAdapter);
                    mNewsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NewsArticles> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onNewsClick(int position) {
        NewsModelClass newsItem = mNews.get(position);
        String newsUrl = newsItem.getUrl();

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        
        int colorInt = Color.parseColor("#F1ECC3");
        CustomTabColorSchemeParams defaultColors = new CustomTabColorSchemeParams.Builder()
                .setToolbarColor(colorInt)
                .build();
        builder.setDefaultColorSchemeParams(defaultColors);
        
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(newsUrl));

    }
}