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
import android.widget.Toast;

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
import com.example.news.data.AppDb;
import com.example.news.data.Article;
import com.example.news.data.IdGenerator;

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

        AppDb db = Room.databaseBuilder(context.getApplicationContext(),
                AppDb.class, "news_app").allowMainThreadQueries().build();
        NewsModelClass currentNewsItem = allNews.get(position);
        holder.title.setText(currentNewsItem.getTitle());
        holder.author.setText(currentNewsItem.getAuthor());
        Article a = db.articleDAO().getByTitle(currentNewsItem.getTitle());

        // If it is already book marked
        if (a != null){
            // Setting icon to fill
            Drawable bookMarkDrawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_bookmark, null);
            holder.bookMarkImage.setImageDrawable(bookMarkDrawable);
        }
        if (currentNewsItem.getUrlToImage() != null)
            Glide.with(holder.itemView.getContext()).load(Uri.parse(currentNewsItem.getUrlToImage())).into(holder.articleImage);

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

        // onClickListener for bookMark Icon
        holder.bookMarkImage.setOnClickListener(v -> {
            Article article = db.articleDAO().getByTitle(currentNewsItem.getTitle());

            // If it is already book marked
            if (article != null){
                // Delete from db
                db.articleDAO().delete(article);
                Toast.makeText(v.getContext(),"Article Removed from Bookmarks!!",Toast.LENGTH_SHORT).show();

                // Setting icon to blank
                Drawable bookMarkDrawable = ResourcesCompat.getDrawable(v.getResources(), R.drawable.ic_bookmark_border, null);
                holder.bookMarkImage.setImageDrawable(bookMarkDrawable);
            }
            else {
                // Adding article to db
                article = new Article();
                article.id = IdGenerator.generateUniqueId();
                article.author = currentNewsItem.getAuthor();
                article.title = currentNewsItem.getTitle();
                article.url = currentNewsItem.getUrl();
                article.urlToImage = currentNewsItem.getUrlToImage();
                db.articleDAO().insertAll(article);
                Toast.makeText(v.getContext(),"Article Added to Bookmarks!!",Toast.LENGTH_SHORT).show();

                // Setting icon to fill
                Drawable bookMarkDrawable = ResourcesCompat.getDrawable(v.getResources(), R.drawable.ic_bookmark, null);
                holder.bookMarkImage.setImageDrawable(bookMarkDrawable);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allNews.size();
    }


    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView articleImage;
        TextView title, author;
        CardView cardView;
        ImageView bookMarkImage;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.ivArticleImage);
            title = itemView.findViewById(R.id.tvHeadline);
            author = itemView.findViewById(R.id.tvAuthor);
            cardView = itemView.findViewById(R.id.cardView);
            bookMarkImage = itemView.findViewById(R.id.ivBookMarkIcon);

        }
    }
    public void UpdateNews(List<NewsModelClass> updatedNews)
    {
        this.allNews = updatedNews;
        notifyDataSetChanged();
    }
}

