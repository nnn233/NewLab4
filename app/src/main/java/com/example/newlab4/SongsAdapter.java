package com.example.newlab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SongsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private ArrayList<SongEntity> songs = new ArrayList<>();

    public SongsAdapter(Context context) {
        this.context = context;
    }

    public void setSongs(ArrayList<SongEntity> songs) {
        this.songs = songs;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongViewHolder(LayoutInflater.from(context).inflate(R.layout.song_view, parent, false), context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        SongViewHolder holder = (SongViewHolder) viewHolder;
        position = holder.getAdapterPosition();

        holder.onBind(songs.get(position));
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
