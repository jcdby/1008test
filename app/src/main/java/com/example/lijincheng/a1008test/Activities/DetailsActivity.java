package com.example.lijincheng.a1008test.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.lijincheng.a1008test.Adapters.PhotosAdapter;
import com.example.lijincheng.a1008test.AsyncTasks.ApiCallAsyncTask;
import com.example.lijincheng.a1008test.Listeners.APICallFinishedListener;
import com.example.lijincheng.a1008test.ModelFactories.ModelFactory;
import com.example.lijincheng.a1008test.Models.Photo;
import com.example.lijincheng.a1008test.R;
import com.example.lijincheng.a1008test.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity implements APICallFinishedListener{

    GridView photosGrid;
    ArrayList<Photo> photos = new ArrayList<>();
    Integer albumId;
    ArrayAdapter<Photo> photoArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        photosGrid = (GridView)findViewById(R.id.photos_grid);
        Intent intent = getIntent();
        albumId =   intent.getIntExtra("photos", -1) + 1;
        photoArrayAdapter = new PhotosAdapter(this,
                 photos);

        String photosAdrs = Utils.getProperity("PhotoAPIAdr",getApplicationContext());

        ApiCallAsyncTask getPhotosTask = new ApiCallAsyncTask();
        getPhotosTask.addFinishTaskListener(this, "getPhotos");
        getPhotosTask.execute(photosAdrs);




    }

    @Override
    public void onFinishedTask(String taskName, String result) {
        try {
            JSONArray jsonPhotos = new JSONArray(result);

            for(int i = 0; i < jsonPhotos.length(); i++){
                JSONObject objPhoto = jsonPhotos.getJSONObject(i);
                if(objPhoto.getInt("albumId") == albumId){
                    Photo photo = ModelFactory.getInstance().generatePhoto(objPhoto);
                    photos.add(photo);
                }
            }
        } catch (JSONException e){
            e.printStackTrace();
        }


        photosGrid.setAdapter(photoArrayAdapter);

    }
}
