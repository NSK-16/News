package com.example.news.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.news.apiUtilities.NewsModelClass;

import java.util.List;

@Dao
public interface IArticleDAO {
	@Query("SELECT * FROM article")
	List<Article> getAll();

	@Query("SELECT * FROM article WHERE id = (:articleId)")
	List<Article> getById(int articleId);

	@Query("SELECT * FROM article WHERE title = (:articleTitle)")
	Article getByTitle(String articleTitle);

	@Insert
	void insertAll(Article... articles);

	@Delete
	void delete(Article article);
}
