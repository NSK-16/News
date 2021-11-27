package com.example.news.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {BookmarkedNews.class},version = 1)
public abstract class BookmarkedNewsDb extends RoomDatabase {
    public abstract NewsDao newsDao();

    private static volatile BookmarkedNewsDb INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BookmarkedNewsDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookmarkedNewsDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookmarkedNewsDb.class, "bookmarkedNews_database").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
