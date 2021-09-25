package com.example.news.activity;

//TODO: * Add the individual fragments,viewpager and tab layout
//      * Beautify the app and support dark mode

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.news.adapters.NewsAdapter;
import com.example.news.R;
import com.example.news.adapters.NewsPagerAdapter;
import com.example.news.apiUtilities.NewsModelClass;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    NewsPagerAdapter newsPagerAdapter;
    String[] tabTitles = new String[]{"HOME","BUSINESS","HEALTH","TECHNOLOGY","SPORTS"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.tbCustom);
        tabLayout = findViewById(R.id.tlCategories); setSupportActionBar(toolbar);

        viewPager2 = findViewById(R.id.vpNews);
        newsPagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(),getLifecycle());
        viewPager2.setAdapter(newsPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(tabTitles[position])).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                newsPagerAdapter.notifyDataSetChanged();
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

//    private void fetchNews() {
//        Call<NewsArticles> call = RetrofitClient.getInstance().getMyApi().getNews("in",API_KEY);
//        call.enqueue(new Callback<NewsArticles>() {
//            @Override
//            public void onResponse(Call<NewsArticles> call, Response<NewsArticles> response) {
//                if(response.isSuccessful()) {
//                    if (!mNews.isEmpty()) {
//                        mNews.clear();
//                    }
//                    mNews=response.body().getArticles();
//                    mNewsAdapter = new NewsAdapter(MainActivity.this::onNewsClick,mNews);
//                    recyclerView.setAdapter(mNewsAdapter);
//                    mNewsAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NewsArticles> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
    }
}