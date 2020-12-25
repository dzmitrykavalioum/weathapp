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
import com.dzmitrykavalioum.weathapp.R
import com.dzmitrykavalioum.weathapp.adapters.WeatherAdapter
import com.dzmitrykavalioum.weathapp.utils.Constants.Companion.APP_ID
import com.dzmitrykavalioum.weathapp.utils.Constants.Companion.METRIC
import com.dzmitrykavalioum.weathapp.utils.GpsLocationHelper

class ForecastFragment : Fragment(),ForecastContract.ViewContract {

    private lateinit var dashboardViewModel: ForecastViewModel
    private lateinit var rvForecast: RecyclerView
    private lateinit var pb:ProgressBar
    private var presenter:ForecastPresenter?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(ForecastViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_forecast, container, false)
//    val textView: TextView = root.findViewById(R.id.text_dashboard)
//    dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//      textView.text = it
//    })
        rvForecast = root.findViewById(R.id.rv_forecast)
        pb = root.findViewById(R.id.pb_forecast)

        //gps location 

        GpsLocationHelper().startListeningUserLocation(requireActivity(),object :GpsLocationHelper.MyLocationListener{
            override fun onLocationChanged(location: Location) {
                if (location!=null){
                Log.d("Forecast fragment", location.latitude.toString()+"\t"+location.longitude.toString())
//                GpsLocationHelper().getCityNameByLocation(requireContext(),location.latitude,location.longitude)
                }
                else{
                    Log.d("Forecast fragment","location is null")
                }
            }
        })

        presenter = ForecastPresenter(this)
        presenter?.getForecastByCity("minsk",METRIC, APP_ID)
        return root
    }

    override fun showForecast(forecastWeather: InfoWeatherV2) {
        rvForecast.adapter = WeatherAdapter(forecastWeather.list)
        rvForecast.layoutManager = LinearLayoutManager(requireContext())
        rvForecast.setHasFixedSize(true)
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        pb.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        pb.visibility = ProgressBar.INVISIBLE
    }
}