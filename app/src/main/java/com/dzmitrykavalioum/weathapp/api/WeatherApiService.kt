package com.dzmitrykavalioum.weathapp.api

import Json4Kotlin_Base
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("forecast")
    fun getByCity(
        @Query("q") city: String,
        @Query("appid") appid: String
    ): List<String>

    @GET("weather")
    suspend fun getTodayWeatherByCity(
        @Query("q") city: String,
        @Query("appid") appid: String
    ): Json4Kotlin_Base


}