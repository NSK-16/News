package com.example.news.apiUtilities;


import com.google.gson.annotations.SerializedName;

public class NewsModelClass {
    @SerializedName("title")
    String title;

    @SerializedName("author")
    String author;

    @SerializedName("url")
    String url;

    @SerializedName("urlToImage")
    String urlToImage;

    public NewsModelClass(String title, String author, String url, String urlToImage) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.urlToImage = urlToImage;
    }


    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrlToImage() {
        return urlToImage;
    }
}
