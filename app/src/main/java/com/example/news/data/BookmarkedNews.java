package com.example.news.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BookmarkedNews")
public class BookmarkedNews {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String author;
    public String url;
    public String urlToImage;
}
