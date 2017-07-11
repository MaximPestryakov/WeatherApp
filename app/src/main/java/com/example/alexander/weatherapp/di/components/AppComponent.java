package com.example.alexander.weatherapp.di.components;

import com.example.alexander.weatherapp.di.modules.AboutModule;
import com.example.alexander.weatherapp.di.modules.AppModule;
import com.example.alexander.weatherapp.di.modules.SettingsModule;
import com.example.alexander.weatherapp.di.modules.WeatherModule;
import com.example.alexander.weatherapp.WeatherApplication;

import dagger.Component;

/**
 * Created by Alexander on 08.07.2017.
 */

@Component(modules = {AppModule.class})
public interface AppComponent {

    AboutComponent plus(AboutModule aboutModule);
    SettingsComponent plus(SettingsModule settingsModule);
    WeatherComponent plus(WeatherModule weatherModule);

    void inject(WeatherApplication app);
}
