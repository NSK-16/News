package com.example.news.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.news.R;
import com.example.news.apiUtilities.NewsModelClass;
import com.example.news.data.BookmarkedNews;
import com.example.news.data.BookmarkedNewsDb;

import java.util.ArrayList;
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

        BookmarkedNewsDb bookmarkedNewsDb = BookmarkedNewsDb.getDatabase(context.getApplicationContext());
        NewsModelClass currentNewsItem = allNews.get(position);
        holder.title.setText(currentNewsItem.getTitle());
        holder.author.setText(currentNewsItem.getAuthor());

        if (currentNewsItem.getUrlToImage() != null)
            Glide.with(holder.itemView.getContext()).load(Uri.parse(currentNewsItem.getUrlToImage())).into(holder.articleImage);

        BookmarkedNews b = bookmarkedNewsDb.newsDao().getByTitle(currentNewsItem.getTitle());

        //If it is already bookmarked
        if(b!=null)
        {
            //Setting the added_icon
            Drawable bookMarkDrawable = ResourcesCompat.getDrawable(context.getResources(),R.drawable.baseline_bookmark_added_black_36,null);
            holder.bookmarkIcon.setImageDrawable(null);
            holder.bookmarkIcon.setImageDrawable(bookMarkDrawable);
        }
        holder.cardView.setOnClickListener(v -> {

            String newsUrl = currentNewsItem.getUrl();

            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

            int colorInt = Color.parseColor("#F1ECC3");
            CustomTabColorSchemeParams defaultColors = new CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(colorInt)
                    .build();
            builder.setDefaultColorSchemeParams(defaultColors);

            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(newsUrl));
        });

        holder.bookmarkIcon.setOnClickListener(v -> {
            BookmarkedNews bookmarkedNews = bookmarkedNewsDb.newsDao().getByTitle(currentNewsItem.getTitle());

            //If it is already bookmarked
            if(bookmarkedNews!=null)
            {
                bookmarkedNewsDb.newsDao().delete(bookmarkedNews);
                //Setting the add_icon
                Drawable bookMarkDrawable = ResourcesCompat.getDrawable(context.getResources(),R.drawable.outline_bookmark_add_black_36,null);
                holder.bookmarkIcon.setImageDrawable(null);
                holder.bookmarkIcon.setImageDrawable(bookMarkDrawable);
            }
            else
            {
                //Adding article to db
                bookmarkedNews = new BookmarkedNews();
                bookmarkedNews.title = currentNewsItem.getTitle();
                bookmarkedNews.author = currentNewsItem.getAuthor();
                bookmarkedNews.url = currentNewsItem.getUrl();
                bookmarkedNews.urlToImage = currentNewsItem.getUrlToImage();

                bookmarkedNewsDb.newsDao().insertAll(bookmarkedNews);
                //Setting the added_icon
                Drawable bookMarkDrawable = ResourcesCompat.getDrawable(context.getResources(),R.drawable.baseline_bookmark_added_black_36,null);
                holder.bookmarkIcon.setImageDrawable(null);
                holder.bookmarkIcon.setImageDrawable(bookMarkDrawable);
            }
        });



    }

    @Override
    public int getItemCount() {
        return allNews.size();
    }


    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView articleImage,bookmarkIcon;
        TextView title, author;
        CardView cardView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.ivArticleImage);
            title = itemView.findViewById(R.id.tvHeadline);
            author = itemView.findViewById(R.id.tvAuthor);
            bookmarkIcon = itemView.findViewById(R.id.ivBookmark);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
    public void UpdateNews(List<NewsModelClass> updatedNews)
    {
        this.allNews = updatedNews;
        notifyDataSetChanged();
    }
}

