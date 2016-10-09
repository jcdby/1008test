package com.example.lijincheng.a1008test.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lijincheng.a1008test.Models.Album;
import com.example.lijincheng.a1008test.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by lijincheng on 10/8/16.
 */

public class AlbumAdapter extends ArrayAdapter<Album> {


    ImageView preview1, preview2, preview3;
    TextView title;

    public AlbumAdapter(Context context, ArrayList<Album> albums) {
        super(context, 0, albums);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Album album = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.album_listview_item, parent, false);
        }

        title = (TextView) convertView.findViewById(R.id.album_title);

        preview1 = (ImageView) convertView.findViewById(R.id.album_preview1);
        preview2 = (ImageView) convertView.findViewById(R.id.album_preview2);
        preview3 = (ImageView) convertView.findViewById(R.id.album_preview3);




        title.setText(album.getTitle());


        //do it later.
        URL url1 = album.getPreview().get(0);
        URL url2 = album.getPreview().get(1);
        URL url3 = album.getPreview().get(2);
        Picasso.with(getContext()).load(url1.toString().replace("http", "https")).resize(100,100).into(preview1);
        Picasso.with(getContext()).load(url2.toString().replace("http", "https")).resize(100,100).into(preview2);
        Picasso.with(getContext()).load(url3.toString().replace("http", "https")).resize(100,100).into(preview3);

        return convertView;

    }


}
