package com.example.news.adapters;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news.R;
import com.example.news.apiUtilities.NewsModelClass;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    Context context;
    List<NewsModelClass> allNews;

    public NewsAdapter(Context context,List<NewsModelClass> allNews) {
        this.context = context;
        this.allNews = allNews;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newsItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(newsItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.cardView.setOnClickListener(v -> {

            NewsModelClass newsItem = allNews.get(position);
            String newsUrl = newsItem.getUrl();

            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

            int colorInt = Color.parseColor("#F1ECC3");
            CustomTabColorSchemeParams defaultColors = new CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(colorInt)
                    .build();
            builder.setDefaultColorSchemeParams(defaultColors);

            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(newsUrl));
        });

        NewsModelClass currentNewsItem = allNews.get(position);
        holder.title.setText(currentNewsItem.getTitle());
        holder.author.setText(currentNewsItem.getAuthor());
        if (currentNewsItem.getUrlToImage() != null)
            Glide.with(holder.itemView.getContext()).load(Uri.parse(currentNewsItem.getUrlToImage())).into(holder.articleImage);

    }

    @Override
    public int getItemCount() {
        return allNews.size();
    }


    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView articleImage;
        TextView title, author;
        CardView cardView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.ivArticleImage);
            title = itemView.findViewById(R.id.tvHeadline);
            author = itemView.findViewById(R.id.tvAuthor);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}

