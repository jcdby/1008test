package com.example.lijincheng.a1008test.Models;

/**
 * Created by lijincheng on 10/8/16.
 */

public class Photo {

    Integer id;
    String title;
    String url;
    String thumnailUrl;
    Integer albumId;

    public Photo() {
        this.id = 0;
        this.title = "";
        this.url = "";
        this.thumnailUrl = "";
        this.albumId = 0;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumnailUrl() {
        return thumnailUrl;
    }

    public void setThumnailUrl(String thumnailUrl) {
        this.thumnailUrl = thumnailUrl;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }


}
