package com.dzmitrykavalioum.weathapp.ui.forecast

import InfoWeatherV2
import android.content.Context

interface ForecastContract {
    interface PresenterContract {
        fun getForecastByCity(city: String, units: String, appid: String)
        fun getForecastByLatLon(lat: Double, lon: Double, units: String, appid: String)
        fun onViewCreated(context: Context)
    }

    interface ViewContract {
        fun showForecast(forecastWeather: InfoWeatherV2)
        fun showError(message: String)
        fun showLoading()
        fun hideLoading()
    }
}