package com.dzmitrykavalioum.weathapp.ui.forecast

import InfoWeatherV2
import android.app.ActionBar
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dzmitrykavalioum.weathapp.MainActivity
import com.dzmitrykavalioum.weathapp.R
import com.dzmitrykavalioum.weathapp.adapters.WeatherAdapter
import com.dzmitrykavalioum.weathapp.utils.Constants.Companion.APP_ID
import com.dzmitrykavalioum.weathapp.utils.Constants.Companion.METRIC
import com.dzmitrykavalioum.weathapp.utils.GpsLocationHelper

class ForecastFragment : Fragment(), ForecastContract.ViewContract {


    private lateinit var rvForecast: RecyclerView
    private lateinit var pb: ProgressBar
    private var presenter: ForecastPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_forecast, container, false)
        rvForecast = root.findViewById(R.id.rv_forecast)
        pb = root.findViewById(R.id.pb_forecast)
        presenter = ForecastPresenter(this).apply {
            onViewCreated(requireContext())
        }
        return root
    }

    override fun showForecast(forecastWeather: InfoWeatherV2) {
        if (activity is MainActivity) {
            (activity as MainActivity).supportActionBar?.title = forecastWeather.city.name
        }
        if (activity!=null&& isAdded) {
            rvForecast.adapter = WeatherAdapter(forecastWeather.list)
            rvForecast.layoutManager = LinearLayoutManager(requireContext())
            rvForecast.setHasFixedSize(true)
        }

    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        pb.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        pb.visibility = ProgressBar.INVISIBLE
    }
}