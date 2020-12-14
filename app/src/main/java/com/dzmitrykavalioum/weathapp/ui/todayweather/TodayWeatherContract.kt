package com.dzmitrykavalioum.weathapp.ui.todayweather

import InfoWeather

interface TodayWeatherContract {
    interface PresenterContract {
        fun getTodayWeather(city: String, units: String, appid: String)
        fun getTodayWeatherByLonLat(lat: Double, lon: Double, units: String, appid: String)
    }

    interface ViewContract {
        fun showTodayWeather(todayWeather: InfoWeather)
        fun showError(message: String)
        fun showLoading()
        fun hideLoading()
    }

}