package com.example.news;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news.apiUtilities.NewsModelClass;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    List<NewsModelClass> allNews;
    private NewsItemClickListener mNewsItemClickListener;

    public NewsAdapter(NewsItemClickListener newsItemClickListener,List<NewsModelClass> allNews)
    {
        this.mNewsItemClickListener = newsItemClickListener;
        this.allNews = allNews;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newsItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new NewsViewHolder(newsItem,mNewsItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
       NewsModelClass currentNewsItem = allNews.get(position);
       holder.title.setText(currentNewsItem.getTitle());
       holder.author.setText(currentNewsItem.getAuthor());
       if(currentNewsItem.getUrlToImage()!=null)
        Glide.with(holder.itemView.getContext()).load(Uri.parse(currentNewsItem.getUrlToImage())).into(holder.articleImage);

    }

    @Override
    public int getItemCount() {
        return allNews.size();
    }



    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView articleImage;
        TextView title,author;
        CardView cardView;
        NewsItemClickListener newsItemClickListener;

        public NewsViewHolder(@NonNull View itemView,NewsItemClickListener newsItemClickListener) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.ivArticleImage);
            title = itemView.findViewById(R.id.tvHeadline);
            author = itemView.findViewById(R.id.tvAuthor);
            cardView = itemView.findViewById(R.id.cardView);
            this.newsItemClickListener = newsItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mNewsItemClickListener.onNewsClick(getBindingAdapterPosition());
        }
    }

    public interface NewsItemClickListener
    {
        void onNewsClick(int position);
    }

}

