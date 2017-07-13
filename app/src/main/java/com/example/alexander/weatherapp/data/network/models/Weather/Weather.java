package com.example.alexander.weatherapp.data.network.models.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexander on 13.07.2017.
 */

public class Weather {

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("main")
    @Expose
    public String main;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("icon")
    @Expose
    public String icon;

}
