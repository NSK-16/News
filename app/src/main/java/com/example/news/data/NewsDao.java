package com.example.news.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NewsDao {
    @Query("Select * from bookmarkednews")
    List<BookmarkedNews> getAll();

    @Insert
    void insertAll(BookmarkedNews... bookmarkedNews);

    @Delete
    void delete(BookmarkedNews bookmarkedNews);

    @Query("Select * from bookmarkednews where title= (:title)")
    BookmarkedNews getByTitle(String title);

    @Query("Select count(*) from bookmarkednews")
    int getCount();

}
