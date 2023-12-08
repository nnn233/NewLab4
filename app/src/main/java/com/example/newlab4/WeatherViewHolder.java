package com.example.newlab4;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    TextView time, temperature, feeling, wind;
    Context context;

    public WeatherViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        this.context = context;

        wind = itemView.findViewById(R.id.wind);
        feeling = itemView.findViewById(R.id.feeling);
        temperature = itemView.findViewById(R.id.temperature);
        time = itemView.findViewById(R.id.time_weather);
    }

    public void onBind(WeatherEntity weather) {
        wind.setText(weather.getWind());
        temperature.setText(weather.getTemperature());
        feeling.setText(weather.getFeeling());
        time.setText(convertToDate(weather.getTime()));
    }

    private String convertToDate(long time) {
        return DateUtils.formatDateTime(context,
                time,
                DateUtils.FORMAT_SHOW_TIME);
    }
}