package com.dzmitrykavalioum.weathapp.ui.home

import Json4Kotlin_Base
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzmitrykavalioum.weathapp.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: Repository): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is New Fragment"
    }


    val text: LiveData<String> = _text

    val myResponce: MutableLiveData<Json4Kotlin_Base> = MutableLiveData()

    fun getTodayWeatherByCity(city:String, appid: String){
        viewModelScope.launch {
            val responce:Json4Kotlin_Base = repository.getTodayWeatherByCity(city,appid)
            myResponce.value = responce
        }
    }


}