package com.example.newlab4;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SongDao {
    @Query("SELECT * FROM songs")
    List<SongEntity> getAll();

    @Query("SELECT * FROM songs WHERE  time = (SELECT MAX(time) FROM songs)")
    SongEntity getLast();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(SongEntity... songs);

}

