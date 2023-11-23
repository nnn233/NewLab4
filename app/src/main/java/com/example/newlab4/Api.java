package com.example.newlab4;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/api_get_current_song")
    Call<SongDto> getSong(@Query("login") String login, @Query("password") String password);
}

