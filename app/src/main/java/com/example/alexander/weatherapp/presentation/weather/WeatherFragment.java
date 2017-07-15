package com.example.alexander.weatherapp.presentation.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alexander.weatherapp.MainActivity;
import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.WeatherApplication;
import com.example.alexander.weatherapp.di.modules.WeatherModule;
import com.example.alexander.weatherapp.presentation.NavigationFragment;
import com.example.alexander.weatherapp.presentation.exceptions.ViewException;
import com.example.alexander.weatherapp.presentation.weather.interfaces.WeatherPresenter;
import com.example.alexander.weatherapp.presentation.weather.interfaces.WeatherView;
import com.example.alexander.weatherapp.presentation.weather.interfaces.models.CityWeather;
import com.example.alexander.weatherapp.views.Layouts.WeatherWidget;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Alexander on 07.07.2017.
 */

public class WeatherFragment extends Fragment implements WeatherView, NavigationFragment{

    @Inject
    WeatherPresenter presenter;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.weather_widget)
    WeatherWidget weatherWidget;

    private Toast toast;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        WeatherApplication.get(getContext()).getAppComponent().plus(new WeatherModule()).inject(this);
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        presenter.bindView(this);
        toast = Toast.makeText(getContext(),null,Toast.LENGTH_LONG);
        ((MainActivity)getActivity()).getToolbar().setTitle(getNavigationName());
        initViewLogic();

    }

    private void initViewLogic() {
        refreshLayout.setOnRefreshListener(() -> presenter.getWeather());
    }

    @Override
    public String getNavigationName() {
        return getContext().getResources().getString(R.string.weather);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unbindView();
        unbinder.unbind();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onError(Throwable cause) {
        ViewException viewException = new ViewException(getContext(),cause);
        toast.setText(viewException.getDetailMessage());
        toast.show();
    }

    @Override
    public void showWeather(CityWeather weatherModel) {
        weatherWidget.setModelAndShow(weatherModel);
    }

    @Override
    public void startProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void finishProgress() {
        refreshLayout.setRefreshing(false);
    }



}
