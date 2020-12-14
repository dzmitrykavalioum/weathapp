package com.dzmitrykavalioum.weathapp.api

import InfoWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("forecast")
    fun getByCity(
        @Query("q") city: String,
        @Query("appid") appid: String
    ): List<String>

    @GET("weather")
    fun getTodayWeatherByCity(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Call<InfoWeather>

    @GET("weather")
    fun getTodayWeatherByLonLat(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Call<InfoWeather>

}