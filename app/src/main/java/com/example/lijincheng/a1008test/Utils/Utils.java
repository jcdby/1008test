package com.example.lijincheng.a1008test.Utils;

import android.content.Context;
import android.util.Log;

import com.example.lijincheng.a1008test.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lijincheng on 10/9/16.
 */

public class Utils {

    public static String getProperity(String key, Context context){
        Properties prop = new Properties();
        InputStream in = context.getResources().openRawResource(R.raw.dev);
        String value = "";

        try{
            prop.load(in);
            value = prop.getProperty(key);
        } catch (IOException e){
            Log.e("Err", e.toString());
        }

        return value;
    }
}
