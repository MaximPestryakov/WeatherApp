package com.example.alexander.weatherapp.presentation.weather.interfaces.models;

import com.example.alexander.weatherapp.LogUtils;

import java.io.Serializable;

/**
 * Created by Alexander on 13.07.2017.
 */

public class CityWeather implements Serializable{

    public static final int STATE_UNKNOWN = -1;
    public static final int STATE_RAIN = 0;
    public static final int STATE_CLOUD = 1;
    public static final int STATE_THUNDERSTORM = 2;
    public static final int STATE_SNOW = 3;
    public static final int STATE_CLEAR= 5;


    private String cityName;
    private Integer cityId;
    private Double temp;
    private Integer press;
    private Integer hum;
    private Integer weatherState;

    public CityWeather(Integer cityId, Integer stateCode, String cityName, Double temp, Integer press, Integer hum) {
        this.cityName = cityName;
        this.temp = temp;
        this.press = press;
        this.hum = hum;
        this.cityId = cityId;
        this.weatherState = stateCode;

        LogUtils.write(" CREATED: " + this);
    }



    public String getCityName() {
        return cityName;
    }


    public Double getTemp() {
        return temp;
    }

    public Integer getPress() {
        return press;
    }

    public Integer getHum() {
        return hum;
    }

    @Override
    public String toString() {
        return "CityWeather{" +
                "cityName='" + cityName + '\'' +
                ", cityId=" + cityId +
                ", temp=" + temp +
                ", press=" + press +
                ", hum=" + hum +
                ", weatherState=" + weatherState +
                '}';
    }

    public Integer getCityId() {
        return cityId;
    }

    public Integer getWeatherState() {
        return weatherState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityWeather that = (CityWeather) o;

        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null)
            return false;
        if (temp != null ? !temp.equals(that.temp) : that.temp != null) return false;
        if (press != null ? !press.equals(that.press) : that.press != null) return false;
        return hum != null ? hum.equals(that.hum) : that.hum == null;

    }

    @Override
    public int hashCode() {
        int result = cityName != null ? cityName.hashCode() : 0;
        result = 31 * result + (temp != null ? temp.hashCode() : 0);
        result = 31 * result + (press != null ? press.hashCode() : 0);
        result = 31 * result + (hum != null ? hum.hashCode() : 0);
        return result;
    }
}
