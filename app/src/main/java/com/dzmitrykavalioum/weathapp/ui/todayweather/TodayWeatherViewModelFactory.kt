package com.dzmitrykavalioum.weathapp.ui.todayweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzmitrykavalioum.weathapp.repository.Repository

class TodayWeatherViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodayWeatherViewModel(repository) as T
    }

}