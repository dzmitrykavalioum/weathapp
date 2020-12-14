package com.dzmitrykavalioum.weathapp.ui.todayweather

import InfoWeather
import android.util.Log
import com.dzmitrykavalioum.weathapp.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodayWeatherPresenter : TodayWeatherContract.PresenterContract {

    private var view: TodayWeatherContract.ViewContract? = null
    private var todayWeather: InfoWeather? = null


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
                Log.d("Presenter", t.message.toString())
                view?.showError(t.message.toString())

            }

        })
    }

    override fun getTodayWeatherByLonLat(lat: Double, lon: Double, units: String, appid: String) {
        val call: Call<InfoWeather> = RetrofitInstance.api.getTodayWeatherByLatLon(lat, lon, units, appid)
        view?.showLoading()
        call?.enqueue(object : Callback<InfoWeather> {
            override fun onResponse(call: Call<InfoWeather>, response: Response<InfoWeather>) {
                todayWeather = response.body()
                todayWeather?.let { view?.showTodayWeather(it) }
                view?.hideLoading()
            }

            override fun onFailure(call: Call<InfoWeather>, t: Throwable) {
                view?.hideLoading()
                Log.d("Presenter", t.message.toString())
                view?.showError(t.message.toString())

            }

        })
    }

}