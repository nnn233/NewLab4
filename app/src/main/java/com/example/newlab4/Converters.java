package com.example.newlab4;

import java.util.Date;

public class Converters {
    public SongEntity convertDtoToEntity(SongDto song){
        String[] data=song.getInfo().split(" – ");
        return new SongEntity(0, data[0], data[1], new Date().getTime());
    }
}
