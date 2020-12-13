package com.dzmitrykavalioum.weathapp.ui.todayweather

import InfoWeather
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzmitrykavalioum.weathapp.repository.Repository
import kotlinx.coroutines.launch

class TodayWeatherViewModel (private val repository: Repository): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is New Fragment"
    }


    val text: LiveData<String> = _text

    val myResponce: MutableLiveData<InfoWeather> = MutableLiveData()

//    fun getTodayWeatherByCity(city:String, units:String,appid: String){
//        viewModelScope.launch {
//            val responce:InfoWeather = repository.getTodayWeatherByCity(city,units,appid)
//            myResponce.value = responce
//        }
//    }


}