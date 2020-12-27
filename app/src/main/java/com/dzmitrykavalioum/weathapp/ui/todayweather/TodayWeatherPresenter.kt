package com.dzmitrykavalioum.weathapp.ui.todayweather

import InfoWeather
import android.content.Context
import android.location.Location
import android.util.Log
import com.dzmitrykavalioum.weathapp.api.RetrofitInstance
import com.dzmitrykavalioum.weathapp.utils.Constants
import com.dzmitrykavalioum.weathapp.utils.GpsLocationHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodayWeatherPresenter(
    private val view: TodayWeatherContract.ViewContract
) : TodayWeatherContract.PresenterContract {

    private var todayWeather: InfoWeather? = null

    override fun onViewCreated(context: Context) {
        getLocationWeather(context)
    }

    override fun getTodayWeather(city: String, units: String, appid: String) {
        val call: Call<InfoWeather> = RetrofitInstance.api.getTodayWeatherByCity(city, units, appid)
        view.showLoading()
        call.enqueue(object : Callback<InfoWeather> {
            override fun onResponse(call: Call<InfoWeather>, response: Response<InfoWeather>) {
                todayWeather = response.body()?.apply {
                    view.showTodayWeather(this)
                }
                view.hideLoading()
            }

            override fun onFailure(call: Call<InfoWeather>, t: Throwable) {
                view.hideLoading()
                Log.d("Presenter", t.message.toString())
                view.showError(t.message.toString())
            }
        })
    }

    override fun getTodayWeatherByLocation(location: Location, units: String, appid: String) {
        val call: Call<InfoWeather> = RetrofitInstance.api.getTodayWeatherByLatLon(
            location.latitude,
            location.longitude,
            units,
            appid
        )
        view.showLoading()
        call.enqueue(object : Callback<InfoWeather> {
            override fun onResponse(call: Call<InfoWeather>, response: Response<InfoWeather>) {
                todayWeather = response.body()
                todayWeather?.let { view.showTodayWeather(it) }
                view.hideLoading()
            }

            override fun onFailure(call: Call<InfoWeather>, t: Throwable) {
                view.hideLoading()
                Log.d("Presenter", t.message.toString())
                view.showError(t.message.toString())
            }
        })
    }

    override fun getTodayWeatherByLonLat(lat: Double, lon: Double, units: String, appid: String) {
        val call: Call<InfoWeather> =
            RetrofitInstance.api.getTodayWeatherByLatLon(lat, lon, units, appid)
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

    private fun getLocationWeather(context: Context) {
        view.showLoading()
        GpsLocationHelper().startListeningUserLocation(context,
            object : GpsLocationHelper.MyLocationListener {
                override fun onLocationChanged(location: Location) {
                    if (location != null) {
                        getTodayWeatherByLocation(
                            location,
                            Constants.METRIC,
                            Constants.APP_ID
                        )
                        Log.d(
                            "Today Weather fragment",
                            location.latitude.toString() + "\t" + location.longitude.toString()
                        )
                    } else {
                        Log.d("Today weather fragment", "location is null")
                    }
                }
            })
    }
}