package com.example.alexander.weatherapp;

import android.util.Log;

/**
 * Created by Alexander on 07.07.2017.
 */

public class LogUtils {

    public static final String common = "COMMON";

    public static void write(Object msg){
        Log.d(common, "-> " + msg);
    }

}
