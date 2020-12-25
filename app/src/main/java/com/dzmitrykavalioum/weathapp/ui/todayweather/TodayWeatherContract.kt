package com.dzmitrykavalioum.weathapp.ui.todayweather

import InfoWeather
import android.location.Location

interface TodayWeatherContract {
    interface PresenterContract {
        fun getTodayWeather(city: String, units: String, appid: String)
        fun getTodayWeatherByLocation(location:Location, units: String, appid: String)
        fun getTodayWeatherByLonLat(lat: Double, lon: Double, units: String, appid: String)
    }

    interface ViewContract {
        fun showTodayWeather(todayWeather: InfoWeather)
        fun showError(message: String)
        fun showLoading()
        fun hideLoading()
    }

}