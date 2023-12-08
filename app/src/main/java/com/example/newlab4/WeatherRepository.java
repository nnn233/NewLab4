package com.example.newlab4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class WeatherRepository {
    private final WeatherDao localDao;

    public WeatherRepository(WeatherDao localDao) {
        this.localDao = localDao;
        refreshItems();
    }

    private final MutableLiveData<List<WeatherEntity>> _items = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<WeatherEntity>> getItems() {
        return _items;
    }

    public void refreshItems() {
        WeatherDatabase.databaseWriteExecutor.execute(() -> _items.postValue(localDao.getAll()));
    }

    public void getWeather() {
        String key = "4c7427211b04e254c8666e0f5c65f705";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Voronezh&appid=" + key + "&units=metric&lang=ru";
        try {
            URL urlU = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)
                    urlU.openConnection();
            urlU.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                buffer.append(line).append("\n");
            JSONObject obj = new JSONObject(buffer.toString());
            String temp = "Температура: " + obj.getJSONObject("main").getInt("temp") + "°C";
            String feeling = "Ощущается как " + obj.getJSONObject("main").getInt("feels_like") + "°C";
            String wind = "Ветер: " + obj.getJSONObject("wind").getInt("speed") + "м/с";
            add(new WeatherEntity(0, temp, feeling, wind, Calendar.getInstance().getTimeInMillis()));
        } catch (IOException | JSONException ignored) {
        }
    }

    public void add(WeatherEntity entity) {
        WeatherDatabase.databaseWriteExecutor.execute(() -> {
            if (localDao.getLast() != null) {
                if (!Objects.equals(localDao.getLast().getTemperature(), entity.getTemperature()) ||
                        !Objects.equals(localDao.getLast().getFeeling(), entity.getFeeling())||
                        !Objects.equals(localDao.getLast().getWind(), entity.getWind()))
                    localDao.insertAll(entity);
            } else localDao.insertAll(entity);
        });
        refreshItems();
    }
}
