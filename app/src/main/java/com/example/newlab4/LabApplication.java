package com.example.newlab4;

import android.app.Application;
import android.content.Context;

import androidx.work.WorkManager;

public class LabApplication extends Application {
    private TaskRepository taskRepository;
    private SongsRepository songsRepository;

    private SongDatabase database ;
    private Api api;
    private static LabApplication application;

    public LabApplication() {

    }
    public static LabApplication getInstance(Context context) {
        return (LabApplication) context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        database=SongDatabase.getDatabase(this.getApplicationContext());
        api= NetworkService.getInstance().getJSONApi();
        taskRepository = new TaskRepository(WorkManager.getInstance(this));
        songsRepository = new SongsRepository(database.songDao(), api);
        taskRepository.getSongPeriodically();
    }

    public SongsRepository getSongsRepository() {
        return songsRepository;
    }
}
