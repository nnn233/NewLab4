package com.example.newlab4;

import android.app.Application;
import android.content.Context;

public class LabApplication extends Application {
    private WeatherRepository weatherRepository;

    public LabApplication() {

    }

    public static LabApplication getInstance(Context context) {
        return (LabApplication) context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        WeatherDatabase database = WeatherDatabase.getDatabase(this.getApplicationContext());
        weatherRepository = new WeatherRepository(database.weatherDao());
    }

    public WeatherRepository getWeatherRepository() {
        return weatherRepository;
    }
}
