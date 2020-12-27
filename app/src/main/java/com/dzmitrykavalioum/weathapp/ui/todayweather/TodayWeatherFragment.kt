package com.dzmitrykavalioum.weathapp.ui.todayweather

import InfoWeather
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dzmitrykavalioum.weathapp.MainActivity
import com.dzmitrykavalioum.weathapp.R
import com.dzmitrykavalioum.weathapp.utils.Constants.Companion.IMAGE_URL
import com.squareup.picasso.Picasso

class TodayWeatherFragment : Fragment(), TodayWeatherContract.ViewContract {
    private var presenter: TodayWeatherPresenter? = null
    private lateinit var tvTemp: TextView
    private lateinit var tvCity: TextView
    private lateinit var tvHumi: TextView
    private lateinit var pbLoad: ProgressBar
    private lateinit var ivWeather: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_today_weather, container, false)


        tvCity = root.findViewById(R.id.tv_city)
        tvTemp = root.findViewById(R.id.tv_temp)
        tvHumi = root.findViewById(R.id.tv_humidity)
        pbLoad = root.findViewById(R.id.pb_load)
        ivWeather = root.findViewById(R.id.iv_weather)
        if (activity is MainActivity){
            (activity as MainActivity).supportActionBar?.title = "Today"
        }
        presenter = TodayWeatherPresenter(this).apply {
            onViewCreated(requireContext())
        }
        return root
    }

    override fun showTodayWeather(todayWeather: InfoWeather) {
        tvCity.text = todayWeather.name.toString()
        tvTemp.text = todayWeather.main.temp.toString()
        tvHumi.text = todayWeather.main.humidity.toString()
        val imageUrl =
            IMAGE_URL + todayWeather.weather.get(0).icon + "@2x.png"
        Picasso.get().load(imageUrl).into(ivWeather)



    }

    override fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        pbLoad.visibility = VISIBLE
    }

    override fun hideLoading() {
        pbLoad.visibility = INVISIBLE

    }

}