package com.example.alexander.weatherapp.di.modules;

import com.example.alexander.weatherapp.business.mappers.WeatherModelToCityWeatherMapper;
import com.example.alexander.weatherapp.business.weather.WeatherInteractor;
import com.example.alexander.weatherapp.business.weather.WeatherInteractorImpl;
import com.example.alexander.weatherapp.data.network.api.GooglePlacesApi;
import com.example.alexander.weatherapp.data.network.api.WeatherApi;
import com.example.alexander.weatherapp.data.repositories.GooglePlacesApiRepository;
import com.example.alexander.weatherapp.data.repositories.GooglePlacesApiRepositoryImpl;
import com.example.alexander.weatherapp.data.repositories.SharedPrefsRepository;
import com.example.alexander.weatherapp.data.repositories.WeatherApiRepository;
import com.example.alexander.weatherapp.data.repositories.WeatherApiRepositoryImpl;
import com.example.alexander.weatherapp.di.scopes.WeatherScope;
import com.example.alexander.weatherapp.job.JobWrapper;
import com.example.alexander.weatherapp.presentation.weather.WeatherPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class WeatherModule {


    @Provides
    @WeatherScope
    WeatherApiRepository provideWeatherApiRepository(WeatherApi weatherApi) {
        return new WeatherApiRepositoryImpl(weatherApi);
    }

    @Provides
    @WeatherScope
    GooglePlacesApiRepository provideGoGApiRepository(GooglePlacesApi googlePlacesApi) {
        return new GooglePlacesApiRepositoryImpl(googlePlacesApi);
    }

    @Provides
    @WeatherScope
    WeatherInteractor provideWeatherInteractor(WeatherApiRepository repository,
                                               WeatherModelToCityWeatherMapper mapper,
                                               SharedPrefsRepository sharedPrefs,
                                               JobWrapper jw,
                                               GooglePlacesApiRepository googlePlacesApiRepository) {
        return new WeatherInteractorImpl(repository, mapper, sharedPrefs, jw, googlePlacesApiRepository);
    }


    @Provides
    @WeatherScope
    WeatherPresenter provideWeatherPresenter(WeatherInteractor interactor) {
        return new WeatherPresenter(interactor);
    }
}
