package com.example.app424;

import android.graphics.Bitmap;

import java.io.Serializable;

public class NoteModel implements Serializable {

    String tit;
    String dis ;
    String URL;
    Bitmap image;
    String time;
    Boolean fav ;

    public Boolean getFav() {
        return fav;
    }

    public void setFav(Boolean fav) {
        this.fav = fav;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public NoteModel(String tit, String dis, String URL, Bitmap image, String time) {
        this.tit = tit;
        this.dis = dis;
        this.URL = URL;
        this.image = image;
        this.time=time;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public NoteModel(String tit, String dis) {
        this.tit = tit;
        this.dis = dis;
    }
    public NoteModel(String tit, String dis,Boolean fav) {
        this.tit = tit;
        this.dis = dis;
        this.fav=fav;
    }
    public NoteModel(){}

    public void setTit(String tit) {
        this.tit = tit;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getTit() {
        return tit;
    }

    public String getDis() {
        return dis;
    }

}
