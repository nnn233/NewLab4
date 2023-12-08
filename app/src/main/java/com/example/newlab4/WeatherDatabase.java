package com.example.newlab4;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {WeatherEntity.class}, version = 1,
        exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();

    private static volatile WeatherDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static WeatherDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WeatherDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    WeatherDatabase.class, "weather_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
