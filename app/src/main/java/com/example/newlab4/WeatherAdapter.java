package com.example.newlab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private ArrayList<WeatherEntity> weatherEntities = new ArrayList<>();

    public WeatherAdapter(Context context) {
        this.context = context;
    }

    public void setWeatherEntities(ArrayList<WeatherEntity> weatherEntities) {
        CommonCallback<WeatherEntity> callback = new CommonCallback<>(this.weatherEntities, weatherEntities);
        this.weatherEntities = weatherEntities;
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeatherViewHolder(LayoutInflater.from(context).inflate(R.layout.weather_view, parent, false), context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        WeatherViewHolder holder = (WeatherViewHolder) viewHolder;
        position = holder.getAdapterPosition();

        holder.onBind(weatherEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return weatherEntities.size();
    }
}
