package com.example.newlab4;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class GetSongWorker extends Worker {

    SongsRepository songsRepository;
    Context context;

    public GetSongWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context=context;
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            songsRepository=LabApplication.getInstance(context).getSongsRepository();
            songsRepository.getSong();
            return Result.success();
        } catch (Exception e) {
            return Result.failure();
        }
    }
}