package com.example.news.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news.R;
import com.example.news.activity.BookMarksActivity;
import com.example.news.adapters.NewsAdapter;
import com.example.news.apiUtilities.NewsModelClass;
import com.example.news.data.BookmarkedNews;
import com.example.news.data.BookmarkedNewsDb;

import java.util.ArrayList;
import java.util.List;

public class BookMarksFragment extends Fragment {

    RecyclerView rvBookmarks;
    List<NewsModelClass> allBookmarkedNews = new ArrayList<>();
    NewsAdapter newsAdapter;

    public BookMarksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_marks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvBookmarks = view.findViewById(R.id.rvBookmarks);
        rvBookmarks.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext(),allBookmarkedNews);
        rvBookmarks.setAdapter(newsAdapter);

        BookmarkedNewsDb db = BookmarkedNewsDb.getDatabase(getContext());
        List<BookmarkedNews> bNews = db.newsDao().getAll();
        for(BookmarkedNews news: bNews)
        {
            NewsModelClass n = new NewsModelClass(news.title,news.author,news.url,news.urlToImage);
            allBookmarkedNews.add(n);
        }

        newsAdapter.UpdateNews(allBookmarkedNews);
    }
}