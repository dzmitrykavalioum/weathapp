package com.dzmitrykavalioum.weathapp.api

import com.dzmitrykavalioum.weathapp.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApi {

    companion object{
        private var retrofit:Retrofit?=null

        val client:Retrofit get() {
            if (retrofit!=null){
                retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            }
            return retrofit!!
        }
    }
}