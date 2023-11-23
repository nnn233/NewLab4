package com.example.newlab4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "songs")
public class SongEntity {
    @PrimaryKey(autoGenerate = true) private int id;
    private String singer;
    private String title;
    private long time;

    public SongEntity(){

    }

    public SongEntity(int id, String singer, String title, long time) {
        this.id = id;
        this.singer = singer;
        this.title = title;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
