package com.example.news.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.news.apiUtilities.NewsModelClass;

@Entity
public class Article {

	@PrimaryKey
	public int id;

	public String title;

	public String author;

	public String url;

	public String urlToImage;

}
