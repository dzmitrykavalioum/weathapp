package com.dzmitrykavalioum.weathapp.ui.forecast

import InfoWeatherV2

interface ForecastContract {
    interface PresenterContract {
        fun getForecastByCity(city: String, units: String, appid: String)
        fun getForecastByLonLat(lat: Double, lon: Double, units: String, appid: String)
    }

    interface ViewContract {
        fun showForecast(forecastWeather: InfoWeatherV2)
        fun showError(message: String)
        fun showLoading()
        fun hideLoading()
    }
}