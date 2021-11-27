package com.example.news.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.news.R;
import com.example.news.adapters.NewsAdapter;
import com.example.news.apiUtilities.NewsModelClass;
import com.example.news.data.BookmarkedNews;
import com.example.news.data.BookmarkedNewsDb;
import com.example.news.fragments.BookMarksFragment;
import com.example.news.fragments.NoBookmarksFragment;

import java.util.ArrayList;
import java.util.List;

public class BookMarksActivity extends AppCompatActivity {
    Toolbar toolbar;
    Fragment fragment = new NoBookmarksFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_marks);
        toolbar = findViewById(R.id.tbBookmarks);
        setSupportActionBar(toolbar);

        BookmarkedNewsDb db = BookmarkedNewsDb.getDatabase(getApplicationContext());
        int count = db.newsDao().getCount();

        if(count!=0)
            fragment = new BookMarksFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frag,fragment);
        transaction.commit();
    }
}