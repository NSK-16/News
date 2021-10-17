package com.example.news.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.news.R;
import com.example.news.adapters.NewsAdapter;
import com.example.news.apiUtilities.NewsArticles;
import com.example.news.apiUtilities.NewsModelClass;
import com.example.news.apiUtilities.RetrofitClient;
import com.example.news.data.AppDb;
import com.example.news.data.Article;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookMarksFragment extends Fragment {
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		recyclerView.setAdapter(null);
		progressBar.setVisibility(View.GONE);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		progressBar.setVisibility(View.INVISIBLE);
	}

	private List<NewsModelClass> news = new ArrayList<>();
	private RecyclerView recyclerView;
	private ProgressBar progressBar;
	NewsAdapter newsAdapter;

	public BookMarksFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		progressBar = view.findViewById(R.id.pbLoading);
		progressBar.setVisibility(View.VISIBLE);
		recyclerView = view.findViewById(R.id.rvHome);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		newsAdapter = new NewsAdapter(getContext(),news);
		recyclerView.setAdapter(newsAdapter);
		AppDb db = Room.databaseBuilder(getContext(),
				AppDb.class, "news_app").allowMainThreadQueries().build();
		List<Article> articles = db.articleDAO().getAll();
		for (Article a:articles) {
			NewsModelClass n = new NewsModelClass(a.title,a.author,a.url,a.urlToImage);
			news.add(n);
		}
		newsAdapter.UpdateNews(news);
		progressBar.setVisibility(View.GONE);
	}

}
