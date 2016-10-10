package com.example.lijincheng.a1008test.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lijincheng.a1008test.Adapters.AlbumAdapter;
import com.example.lijincheng.a1008test.AsyncTasks.ApiCallAsyncTask;
import com.example.lijincheng.a1008test.Listeners.APICallFinishedListener;
import com.example.lijincheng.a1008test.ModelFactories.ModelFactory;
import com.example.lijincheng.a1008test.Models.Album;
import com.example.lijincheng.a1008test.Models.Photo;
import com.example.lijincheng.a1008test.R;
import com.example.lijincheng.a1008test.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements APICallFinishedListener {

    ListView AlbumView;
    ArrayList<Album> albums = new ArrayList<Album>();
    ArrayList<Photo> photos = new ArrayList<Photo>();
    AlbumAdapter albumAdapter;
//    ArrayAdapter<Album> albumAdapter = new ArrayAdapter<Album>(this, R.layout.album_listview_item, albums);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlbumView = (ListView)findViewById(R.id.album_listview);
        albumAdapter = new AlbumAdapter(this,albums);


        String albumAdrs = Utils.getProperity("AlbumAPIAdr",getApplicationContext());
        String photosAdrs = Utils.getProperity("PhotoAPIAdr",getApplicationContext());

        ApiCallAsyncTask getAlbumTask = new ApiCallAsyncTask();
        getAlbumTask.addFinishTaskListener(this, "album");
        getAlbumTask.execute(albumAdrs);

        ApiCallAsyncTask getPhotoTask = new ApiCallAsyncTask();
        getPhotoTask.addFinishTaskListener(this,"photo");
        getPhotoTask.execute(photosAdrs);




        AlbumView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("photos",i);
                getApplicationContext().startActivity(intent);
            }
        });



    }

    @Override
    public void onFinishedTask(String taskName, String result) {
        switch (taskName){
            case "album":
                generateAlbums(result, this.albums);
                break;
            case "photo":
                generatePhotos(result, this.photos);
                break;
            default:
                return;

        }
    }


    public void generateAlbums(String result, ArrayList<Album> albums){

        try {
            JSONArray jsonAlbums = new JSONArray(result);

            for(int i = 0; i < jsonAlbums.length(); i++){
                JSONObject objAlbum = jsonAlbums.getJSONObject(i);
                Album album = ModelFactory.getInstance().generateAlbum(objAlbum);
                albums.add(album);
            }
        } catch (JSONException e){
            Log.e("Json Exception", e.toString());
        }

    }


    public void generatePhotos(String result, ArrayList<Photo> photos) {
        try {
            JSONArray jsonPhotos = new JSONArray(result);
            for(int i = 0; i < jsonPhotos.length(); i++){
                JSONObject objPhoto = jsonPhotos.getJSONObject(i);
                Photo photo = ModelFactory.getInstance().generatePhoto(objPhoto);
                photos.add(photo);
            }
            mappingPhotos(this.albums, this.photos);
            this.AlbumView.setAdapter(albumAdapter);

        } catch (JSONException e){
            Log.e("Json Exception", e.toString());
        }
    }


    public void mappingPhotos(ArrayList<Album> albums, ArrayList<Photo> photos){

        for (Photo photo: photos
             ) {
                albums.get(photo.getAlbumId()-1).addPhoto(photo);

        }
    }




}
