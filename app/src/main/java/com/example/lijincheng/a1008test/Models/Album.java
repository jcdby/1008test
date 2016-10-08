package com.example.lijincheng.a1008test.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by lijincheng on 10/8/16.
 */

public class Album{

    Integer id;
    String title;
    ArrayList<Photo> photos;
    ArrayList<URL> preview;


    public Album() {
        super();
        this.id = 0;
        this.title = "";
        this.photos = new ArrayList<Photo>();
        this.preview = new ArrayList<URL>();

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

    public ArrayList<URL> getPreview() {
        ArrayList<Photo> previewPhotos = new ArrayList<Photo>();



        for (Photo previewPhoto: this.photos.subList(0,2)
             ) {
            try {
                String stringUrl = previewPhoto.getUrl();
                URL url = new URL(stringUrl);
                this.preview.add(url);
            } catch (MalformedURLException e){
                Log.e("Error", e.toString());
            }

        }

        return preview;
    }

    public void setPreview(ArrayList<URL> preview) {
        this.preview = preview;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public void addPhoto(Photo photo){
        photos.add(photo);
    }



}
