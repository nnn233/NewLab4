package com.example.newlab4;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SongsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        setAdapter();
        recyclerView.setAdapter(adapter);

        if (!checkInternetConnection())
            Toast.makeText(this, "Доступен только просмотр внесенных ранее записей", Toast.LENGTH_SHORT).show();

    }

    private void setAdapter() {
        adapter = new SongsAdapter(MainActivity.this);
        LabApplication app=LabApplication.getInstance(this);
        SongsRepository repository= app.getSongsRepository();
        repository.getItems().observe(this, items -> {
            if (!items.isEmpty())
                adapter.setSongs((ArrayList<SongEntity>) items);
        });
    }

    public boolean checkInternetConnection() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr != null) {
            NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    return true;
                } else return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            }
        }
        return false;
    }

}