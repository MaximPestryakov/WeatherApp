package com.example.alexander.weatherapp.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.data.prefs.SharedPrefs;
import com.example.alexander.weatherapp.data.repositories.SharedPrefsRepository;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private final Context appContext;


    public AppModule(@NonNull Context appContext) {
        this.appContext = appContext;

    }

    @Provides
    @Singleton
    Context provideContext() {
        return appContext;
    }

    @Provides
    GsonBuilder provideGsonBuilder() {
        return new GsonBuilder();
    }


    @Provides
    @Singleton
    SharedPrefsRepository provideSharedPrefsRepository(GsonBuilder gsonBuilder) {
        return new SharedPrefs(appContext, gsonBuilder);
    }

    @Provides
    WeatherModelToCityWeatherMapper provideMapper() {
        return new WeatherModelToCityWeatherMapper();
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }
}
