package com.example.news.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.news.R;
import com.example.news.adapters.NewsPagerAdapter;
import com.example.news.data.BookmarkedNews;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity{
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    NewsPagerAdapter newsPagerAdapter;
    TabLayoutMediator tabLayoutMediator;
    String[] tabTitles = new String[]{"HOME","BUSINESS","HEALTH","TECHNOLOGY","SPORTS"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.tbCustom);
        setSupportActionBar(toolbar);
        tabLayout = findViewById(R.id.tlCategories);

        viewPager2 = findViewById(R.id.vpNews);
        newsPagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(),getLifecycle());
        viewPager2.setAdapter(newsPagerAdapter);



        tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(tabTitles[position]));
        tabLayoutMediator.attach();

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
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_bookmarks) {
            Intent intent = new Intent(this, BookMarksActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tabLayoutMediator.detach();
        viewPager2.setAdapter(null);
    }
}