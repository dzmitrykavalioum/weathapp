package com.dzmitrykavalioum.weathapp.api

import com.dzmitrykavalioum.weathapp.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val interceptor = HttpLoggingInterceptor()


    private val retrofit by lazy {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client:OkHttpClient = OkHttpClient().newBuilder().addNetworkInterceptor(interceptor).build()
        Retrofit.Builder()

            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api:WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)

    }
}