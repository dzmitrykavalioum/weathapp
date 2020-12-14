package com.dzmitrykavalioum.weathapp.ui.forecast

import InfoWeatherV2
import android.util.Log
import com.dzmitrykavalioum.weathapp.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastPresenter : ForecastContract.PresenterContract {

    private var view: ForecastContract.ViewContract? = null
    private var todayWeather: InfoWeatherV2? = null


    constructor(view: ForecastContract.ViewContract?) {
        this.view = view
    }


    override fun getForecastByCity(city: String, units: String, appid: String) {
        val call: Call<InfoWeatherV2> = RetrofitInstance.api.getForecastByCity(city, units, appid)
        view?.showLoading()
        call?.enqueue(object : Callback<InfoWeatherV2> {
            override fun onResponse(call: Call<InfoWeatherV2>, response: Response<InfoWeatherV2>) {
                todayWeather = response.body()
                todayWeather?.let { view?.showForecast(it) }
                view?.hideLoading()
            }

            override fun onFailure(call: Call<InfoWeatherV2>, t: Throwable) {
                view?.hideLoading()
                Log.d("Presenter", t.message.toString())
                view?.showError(t.message.toString())

            }

        })
    }

    override fun getForecastByLonLat(lat: Double, lon: Double, units: String, appid: String) {
        TODO("Not yet implemented")
    }
}