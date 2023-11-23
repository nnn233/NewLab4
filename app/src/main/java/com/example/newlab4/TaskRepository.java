package com.example.newlab4;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class TaskRepository {
    private WorkManager workManager;

    public TaskRepository(WorkManager workManager) {
        this.workManager = workManager;
    }

    private final int REFRESH_RATE_SECONDS = 20;
    private final String GET_CURRENT_SONG = "refreshLatestItemsTask";

    public void getSongPeriodically() {
        PeriodicWorkRequest myWorkRequest = new PeriodicWorkRequest.Builder(GetSongWorker.class, REFRESH_RATE_SECONDS, TimeUnit.SECONDS)
                .build();
        workManager.enqueueUniquePeriodicWork(
                GET_CURRENT_SONG,
                ExistingPeriodicWorkPolicy.KEEP,
                myWorkRequest
        );
    }

}
