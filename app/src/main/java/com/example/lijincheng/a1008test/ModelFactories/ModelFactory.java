package com.example.lijincheng.a1008test.ModelFactories;

import android.util.Log;

import com.example.lijincheng.a1008test.Models.Album;
import com.example.lijincheng.a1008test.Models.Photo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lijincheng on 10/10/16.
 */

public class ModelFactory {

    static ModelFactory modelFactory = null;

    public ModelFactory(){

    }

    public static ModelFactory getInstance(){
        if(modelFactory == null){
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    public Photo generatePhoto(JSONObject jsObj){
        Photo photo = new Photo();
        try {
            photo.setId(jsObj.getInt("id"));
            photo.setTitle(jsObj.getString("title"));
            photo.setThumnailUrl(jsObj.getString("thumbnailUrl"));
            photo.setUrl(jsObj.getString("url"));
            photo.setAlbumId(jsObj.getInt("albumId"));
        }catch (JSONException e){
            Log.e("Json Error", e.toString());
        }

        return photo;

    }


    public Album generateAlbum(JSONObject JsObj){
        Album album = new Album();
        try {
            album.setId(JsObj.getInt("id"));
            album.setTitle(JsObj.getString("title"));
        } catch (JSONException e){
            Log.e("Json Error", e.toString());
        }

        return album;
    }


}
