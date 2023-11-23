package com.example.newlab4;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongDto {
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("info")
    @Expose
    private String info;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
