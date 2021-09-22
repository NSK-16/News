package com.example.news.apiUtilities;

import com.example.news.apiUtilities.NewsModelClass;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsArticles {
    @SerializedName("articles")
    List<NewsModelClass> articles;

    public List<NewsModelClass> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsModelClass> articles) {
        this.articles = articles;
    }
}
