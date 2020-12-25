package com.dzmitrykavalioum.weathapp.ui.todayweather

import InfoWeather
import android.app.Application
import android.location.Location
import android.util.Log
import androidx.lifecycle.*
import com.dzmitrykavalioum.weathapp.utils.Constants
import com.dzmitrykavalioum.weathapp.utils.GpsLocationHelper

import kotlinx.coroutines.launch

class TodayWeatherViewModel (application: Application): AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is New Fragment"
    }


    val text: LiveData<String> = _text

    val myResponce: MutableLiveData<InfoWeather> = MutableLiveData()




}