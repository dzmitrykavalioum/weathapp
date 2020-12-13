package com.dzmitrykavalioum.weathapp.ui.todayweather

import InfoWeather
import com.dzmitrykavalioum.weathapp.api.RetrofitInstance
import com.dzmitrykavalioum.weathapp.api.WeatherApi
import com.dzmitrykavalioum.weathapp.api.WeatherApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodayWeatherPresenter : TodayWeatherContract.PresenterContract {

    private var view: TodayWeatherContract.ViewContract? = null
    private var todayWeather: InfoWeather? = null
    private var apiClient: RetrofitInstance? = null
//    init {
//        apiClient =  WeatherApi.client()
//    }

    constructor(view: TodayWeatherContract.ViewContract?) {
        this.view = view
    }


    override fun getTodayWeather(city: String, units: String, appid: String) {
        val call: Call<InfoWeather> = RetrofitInstance.api.getTodayWeatherByCity(city, units, appid)
        view?.showLoading()
        call?.enqueue(object : Callback<InfoWeather> {
            override fun onResponse(call: Call<InfoWeather>, response: Response<InfoWeather>) {
                todayWeather = response.body()
                todayWeather?.let { view?.showTodayWeather(it) }
                view?.hideLoading()
            }

            override fun onFailure(call: Call<InfoWeather>, t: Throwable) {
                view?.hideLoading()
                view?.showError(t.message.toString())

            }

        })
    }

}