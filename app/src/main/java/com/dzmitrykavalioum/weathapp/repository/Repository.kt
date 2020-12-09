package com.dzmitrykavalioum.weathapp.repository

import Json4Kotlin_Base
import com.dzmitrykavalioum.weathapp.api.RetrofitInstance

class Repository {
    suspend fun getTodayWeatherByCity (city:String,appid:String):Json4Kotlin_Base{
        return RetrofitInstance.api.getTodayWeatherByCity(city,appid)
    }
}