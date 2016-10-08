package com.example.lijincheng.a1008test.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lijincheng.a1008test.Listeners.DownLoadFinishedLIstener;
import com.example.lijincheng.a1008test.Models.Album;
import com.example.lijincheng.a1008test.R;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by lijincheng on 10/8/16.
 */

public class AlbumAdapter extends ArrayAdapter<Album> implements DownLoadFinishedLIstener {


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

        URL url1 = album.getPreview().get(0);
        URL url2 = album.getPreview().get(1);
        URL url3 = album.getPreview().get(2);

        DownloadBitTask downloadBitTask = new DownloadBitTask();
        downloadBitTask.addListener(this);
        downloadBitTask.execute(url1,url2, url3);







        // Return the completed view to render on screen
        return convertView;

    }

    @Override
    public void downLoadFinished(ArrayList<Bitmap> bitmaps) {
        this.preview1.setImageBitmap(bitmaps.get(0));
        this.preview2.setImageBitmap(bitmaps.get(1));
        this.preview3.setImageBitmap(bitmaps.get(2));
    }

    private class DownloadBitTask extends AsyncTask<URL, Void, ArrayList<Bitmap>> {

        ArrayList<DownLoadFinishedLIstener> listeners = new ArrayList<>();


        public void addListener(DownLoadFinishedLIstener listener){
            listeners.add(listener);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<Bitmap> bitmaps) {
            super.onPostExecute(bitmaps);
            for (DownLoadFinishedLIstener listener: listeners
                    ) {
                listener.downLoadFinished(bitmaps);
            }
        }

        @Override
        protected ArrayList<Bitmap> doInBackground(URL... urls) {

            ArrayList<Bitmap> bitmaps = new ArrayList<>();

            try {
                Bitmap bmp1 = BitmapFactory.decodeStream(urls[0].openConnection().getInputStream());
                Bitmap bmp2 = BitmapFactory.decodeStream(urls[1].openConnection().getInputStream());
                Bitmap bmp3 = BitmapFactory.decodeStream(urls[2].openConnection().getInputStream());

                bitmaps.add(bmp1);
                bitmaps.add(bmp2);
                bitmaps.add(bmp3);



            }catch (IOException e) {
                Log.e("Error", e.toString());
            }finally {

            }


            return bitmaps;
        }
    }
}
