package com.example.alexander.weatherapp.presentation.weather;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.data.network.models.weather.Forecast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private final DateFormat dateFormat = new SimpleDateFormat("EEE, d MMMM");

    private List<Forecast> forecastList;

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        holder.bind(forecastList.get(position));

    }

    @Override
    public int getItemCount() {
        if (forecastList == null) {
            return 0;
        }
        return forecastList.size();
    }

    public void setForecastList(List<Forecast> forecastList) {
        this.forecastList = forecastList;
        notifyDataSetChanged();
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.forecast_icon)
        ImageView forecastIcon;

        @BindView(R.id.temperature)
        TextView temperature;

        ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Forecast forecast) {
            Context context = itemView.getContext();

            date.setText(dateFormat.format(new Date(forecast.getDt() * 1000)));

            Drawable drawable;
            switch (forecast.getWeatherId() / 100) {
                case 2:
                    drawable = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_storm, null);
                    break;

                case 5:
                    drawable = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_rain, null);
                    break;

                case 6:
                    drawable = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_snow, null);
                    break;

                case 8:
                    drawable = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_cloudy, null);
                    break;

                default:
                    drawable = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_cloudy, null);
                    break;
            }
            if (forecast.getWeatherId() == 800) {
                drawable = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_sun, null);
            }
            forecastIcon.setImageDrawable(drawable);

            int temp = (int) Math.round(forecast.getTemp() - 273.15);
            String temperatureText = context.getString(R.string.temperature_celsius, temp);
            if (temp > 0) {
                temperatureText = "+" + temperatureText;
            }
            temperature.setText(temperatureText);
        }
    }
}
