package com.example.news.activity;

//TODO: * Add the individual fragments,viewpager and tab layout
//      * Beautify the app and support dark mode

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.news.adapters.NewsAdapter;
import com.example.news.R;
import com.example.news.adapters.NewsPagerAdapter;
import com.example.news.apiUtilities.NewsArticles;
import com.example.news.apiUtilities.NewsModelClass;
import com.example.news.apiUtilities.RetrofitClient;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsAdapter.NewsItemClickListener {
    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem tbItemHome,tbItemBusiness,tbItemTechnology,tbItemHealth,tbItemSports;
    ViewPager2 viewPager2;
    NewsPagerAdapter newsPagerAdapter;
    String[] tabTitles = new String[]{"HOME","BUSINESS","HEALTH","TECHNOLOGY","SPORTS"};

    List<NewsModelClass> mNews = new ArrayList<>();
    NewsAdapter mNewsAdapter;

    public String API_KEY = "ba88d060a3e049ca9fa46f2bea0d52c4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.tbCustom);
        tabLayout = findViewById(R.id.tlCategories);
        setSupportActionBar(toolbar);
        tbItemHome = findViewById(R.id.tbItemHome);
        tbItemBusiness = findViewById(R.id.tbItemBusiness);
        tbItemHealth = findViewById(R.id.tbItemHealth);
        tbItemTechnology = findViewById(R.id.tbItemTechnology);
        tbItemSports = findViewById(R.id.tbItemSports);

        newsPagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(),getLifecycle());
        viewPager2 = findViewById(R.id.vpNews);
        viewPager2.setAdapter(newsPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText(tabTitles[position]);
        }).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
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

    public void fetchNews(RecyclerView recyclerView, String category) {

        Call<NewsArticles> call = RetrofitClient.getInstance().getMyApi().getNews("in",category,100,API_KEY);
        call.enqueue(new Callback<NewsArticles>() {
            @Override
            public void onResponse(Call<NewsArticles> call, Response<NewsArticles> response) {
                if(response.isSuccessful()) {
                    if (!mNews.isEmpty()) {
                        mNews.clear();
                    }
                    mNews=response.body().getArticles();
                    mNewsAdapter = new NewsAdapter(MainActivity.this,mNews);
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

}