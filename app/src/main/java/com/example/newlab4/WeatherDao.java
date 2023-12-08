package com.example.newlab4;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeatherDao {
    @Query("SELECT * FROM weather ORDER BY time")
    List<WeatherEntity> getAll();

    @Query("SELECT * FROM weather WHERE  time = (SELECT MAX(time) FROM weather)")
    WeatherEntity getLast();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(WeatherEntity... songs);

}

