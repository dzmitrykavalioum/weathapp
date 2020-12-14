package com.dzmitrykavalioum.weathapp.api

import InfoWeather
import InfoWeatherV2
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("forecast")
    fun getForecastByLatLon(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Call<InfoWeatherV2>

    @GET("forecast")
    fun getForecastByCity(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Call<InfoWeatherV2>


    @GET("weather")
    fun getTodayWeatherByCity(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Call<InfoWeather>

    @GET("weather")
    fun getTodayWeatherByLatLon(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Call<InfoWeather>

}