package com.example.news.data;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Article.class}, version = 1)
public abstract class AppDb extends RoomDatabase {
	public abstract IArticleDAO articleDAO();
}
