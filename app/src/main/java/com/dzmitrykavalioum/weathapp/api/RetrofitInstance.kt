package com.dzmitrykavalioum.weathapp.api

import com.dzmitrykavalioum.weathapp.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api:WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)

    }
}