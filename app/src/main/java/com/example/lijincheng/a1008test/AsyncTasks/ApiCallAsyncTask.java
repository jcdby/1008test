package com.example.lijincheng.a1008test.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.lijincheng.a1008test.Listeners.APICallFinishedListener;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created by lijincheng on 10/8/16.
 */

public class ApiCallAsyncTask extends AsyncTask<String, Void, String> {



    ArrayList<APICallFinishedListener> listeners = new ArrayList<APICallFinishedListener>();
    String taskName = "";


    @Override
    protected String doInBackground(String... urls) {
        // The connection URL
        String url = urls[0];

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String
        String result = restTemplate.getForObject(url, String.class);
        Log.i("GotDATA", result);
        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        for (APICallFinishedListener l: listeners
             ) {
            l.onFinishedTask(this.getTaskName(),s);
        }

    }

    public void addFinishTaskListener(APICallFinishedListener listener, String name){
        listeners.add(listener);
        this.setTaskName(name);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public ApiCallAsyncTask() {
        super();
    }
}
