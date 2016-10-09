package com.example.lijincheng.a1008test.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lijincheng.a1008test.Models.Photo;
import com.example.lijincheng.a1008test.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lijincheng on 10/8/16.
 */

public class PhotosAdapter extends ArrayAdapter<Photo> {

    TextView title;
    ImageView photoImage;

    public PhotosAdapter(Context context, ArrayList<Photo> photos) {
        super(context, 0, photos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Photo photo = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_gridview_item, parent, false);
        }

        title = (TextView) convertView.findViewById(R.id.photo_title);
        photoImage = (ImageView) convertView.findViewById(R.id.photo_img);
        title.setText(photo.getTitle().substring(0,8) + "...");



        Picasso.with(getContext()).load(photo.getUrl().replace("http", "https")).resize(150,150).into(photoImage);

        return convertView;

    }
}
