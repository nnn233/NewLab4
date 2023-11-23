package com.example.newlab4;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SongEntity.class}, version = 1,
        exportSchema = false)
public abstract class SongDatabase extends RoomDatabase {
    public abstract SongDao songDao();

    private static volatile SongDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SongDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SongDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SongDatabase.class, "songs_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
