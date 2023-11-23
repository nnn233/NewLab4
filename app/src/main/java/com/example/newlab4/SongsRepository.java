package com.example.newlab4;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongsRepository {
    private final SongDao localDao;
    private final Api api;
    private final Converters converters;

    public SongsRepository(SongDao localDao, Api api) {
        this.localDao = localDao;
        this.api = api;
        converters = new Converters();
        refreshItems();
    }

    private final MutableLiveData<List<SongEntity>> _items = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<SongEntity>> getItems() {
        return _items;
    }

    public void refreshItems() {
        SongDatabase.databaseWriteExecutor.execute(() -> _items.postValue(localDao.getAll()));
    }

    public void getSong() {
        api.getSong("4707login", "4707pass")
                .enqueue(new Callback<SongDto>() {
                    @Override
                    public void onResponse(@NonNull Call<SongDto> call, @NonNull Response<SongDto> response) {
                        SongDto songDto = response.body();
                        Log.e("Query", "" + response.code());
                        add(songDto);
                    }

                    @Override
                    public void onFailure(@NonNull Call<SongDto> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public void add(SongDto songDto) {
        SongDatabase.databaseWriteExecutor.execute(() -> {
            SongEntity song = converters.convertDtoToEntity(songDto);
            if (!Objects.equals(localDao.getLast().getTitle(), song.getTitle()))
                localDao.insertAll(song);
        });
        refreshItems();
    }


}
