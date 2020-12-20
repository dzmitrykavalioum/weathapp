package com.dzmitrykavalioum.weathapp.ui.todayweather

import InfoWeather
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

class TodayWeatherViewModel (): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is New Fragment"
    }


    val text: LiveData<String> = _text

    val myResponce: MutableLiveData<InfoWeather> = MutableLiveData()


}