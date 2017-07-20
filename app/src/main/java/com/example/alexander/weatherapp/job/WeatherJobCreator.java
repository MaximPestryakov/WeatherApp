package com.example.alexander.weatherapp.job;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;
import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.data.repositories.SharedPrefsRepository;


public class WeatherJobCreator implements JobCreator {


    private WeatherApi weatherApi;
    private WeatherModelToCityWeatherMapper mapper;
    private SharedPrefsRepository prefs;


    public WeatherJobCreator(WeatherApi weatherApi, WeatherModelToCityWeatherMapper mapper, SharedPrefsRepository prefs) {
        this.weatherApi = weatherApi;
        this.mapper = mapper;
        this.prefs = prefs;

    }

    @Override
    public Job create(String tag) {
        switch (tag) {
            case WeatherJob.TAG:
                //error fix Job for tag GET_WEATHER_JOB was already run, a creator should always create a new Job instance
                return new WeatherJob(weatherApi, mapper, prefs);
            default:
                return null;
        }

    }
}