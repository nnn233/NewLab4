package com.example.newlab4;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SongViewHolder extends RecyclerView.ViewHolder {
    TextView id, title, singer, time;
    Context context;

    public SongViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        this.context = context;

        id = itemView.findViewById(R.id.id_song);
        title = itemView.findViewById(R.id.title_song);
        singer = itemView.findViewById(R.id.singer);
        time = itemView.findViewById(R.id.time_song);
    }

    public void onBind(SongEntity note) {
        id.setText(note.getId());
        title.setText(note.getTitle());
        singer.setText(note.getSinger());
        time.setText(convertToDate(note.getTime()));
    }

    private static String convertToDate(long time) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy MM:ss", Locale.getDefault());
        return format.format(date);
    }
}