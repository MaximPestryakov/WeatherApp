package com.example.alexander.weatherapp.views.Layouts;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather;

import java.util.Locale;

import static com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather.STATE_CLEAR;
import static com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather.STATE_CLOUD;
import static com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather.STATE_RAIN;
import static com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather.STATE_SNOW;
import static com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather.STATE_THUNDERSTORM;
import static com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather.STATE_UNKNOWN;

/**
 * Created by Alexander on 13.07.2017.
 */

/**
 * Вьюшка, отображающая погоду
 */

public class WeatherWidget extends RelativeLayout {

    CityWeather model;
    float kelwinCoef = 273.15f;

    public void setModelAndShow(CityWeather model) {
        if(!model.equals(this.model)) {
            this.model = model;
            inflateContent();
        }
    }

    private void inflateContent(){
        clearView();

        LayoutInflater layoutInflater = (LayoutInflater ) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout layout = (RelativeLayout) layoutInflater.inflate(R.layout.weather_widget_layout,null);

        ImageView weatherIcon = (ImageView) layout.findViewById(R.id.weather_icon);
        TextView cityTextView = (TextView) layout.findViewById(R.id.city);
        TextView temperatureTextView = (TextView) layout.findViewById(R.id.temperature);
        TextView pressureTextView = (TextView) layout.findViewById(R.id.pressure);
        TextView humidityTextView = (TextView) layout.findViewById(R.id.humidity);

        cityTextView.setText(getTranslatableCity(model.getCityId()));

        weatherIcon.setImageDrawable(getDrawableByStateCode(model.getWeatherState()));

        int press = (int) (model.getPress()*0.750062f);
        String pressure = String.valueOf(press) + " " + getResources().getString(R.string.pressure);
        pressureTextView.setText(pressure);

        String hum = String.valueOf(model.getHum())+"%";
        humidityTextView.setText(hum);

        //В зависимости от региона (США или др, показывает время в кельвинах или цельсиях)
        temperatureTextView.setText(getLocaleTemp(model.getTemp()));


        addView(layout);

    }

    private Drawable getDrawableByStateCode(Integer weatherState) {

        Drawable drawable = null;

        switch (weatherState){
            case STATE_RAIN:
                drawable = getResources().getDrawable(R.drawable.ic_rain);
                break;
            case STATE_CLEAR:
                drawable = getResources().getDrawable(R.drawable.ic_cloudy);
                break;
            case STATE_CLOUD:
                drawable = getResources().getDrawable(R.drawable.ic_cloudy);
                break;
            case STATE_THUNDERSTORM:
                drawable = getResources().getDrawable(R.drawable.ic_storm);
                break;
            case STATE_UNKNOWN:
                drawable = getResources().getDrawable(R.drawable.ic_cloudy);
                break;
            case STATE_SNOW:
                drawable = getResources().getDrawable(R.drawable.ic_snow);
                break;
        }

        return drawable;
    }

    private String getLocaleTemp(Double temp) {
        String tresult;
        boolean fahrenheit = false;
        String[] countriesWithFahrenheit = getResources().getStringArray(R.array.fahrenheit_countries);
        for(String country : countriesWithFahrenheit){
            if(country.equals(Locale.getDefault().getCountry())){
                fahrenheit = true;
                break;
            }
        }

        if(fahrenheit) {
            tresult = ((int)(temp*9/5-459.67)) + getResources().getString(R.string.fahrenheit);

        } else {
            int ctemp = (int) (temp - kelwinCoef);
            String d = ctemp < 0 ? "-" : ctemp==0 ? "" :  "+";
            tresult = d + ctemp + getResources().getString(R.string.celsius);
        }

        return tresult;
    }

    private void clearView() {
        if(getChildCount()>0){
            removeAllViews();
        }
    }

    public WeatherWidget(Context context) {
        super(context);
    }

    public WeatherWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeatherWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public String getTranslatableCity(Integer cityId) {
        //TODO локализация города, пока только москва
        String result = "";
        switch (cityId){
            case 7323:
                result = getResources().getString(R.string.moscow);
                break;
        }

        return result;
    }
}
