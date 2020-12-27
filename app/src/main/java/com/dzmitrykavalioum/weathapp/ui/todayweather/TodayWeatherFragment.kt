package com.dzmitrykavalioum.weathapp.ui.todayweather

import InfoWeather
import android.app.ProgressDialog
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
import java.lang.Exception

class TodayWeatherFragment : Fragment(), TodayWeatherContract.ViewContract {
    private var presenter: TodayWeatherPresenter? = null
    private lateinit var tvTemp: TextView
    private lateinit var tvCity: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvWind: TextView
    private lateinit var tvCloud: TextView
    private lateinit var tvPressure: TextView
    private lateinit var pbLoad: ProgressBar
    private lateinit var ivWeather: ImageView
    private var progressDialog:ProgressDialog?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_today_weather_new, container, false)


        tvCity = root.findViewById(R.id.tv_city)
        tvTemp = root.findViewById(R.id.tv_temp)
        tvCloud = root.findViewById(R.id.tv_cloud)
        tvDescription = root.findViewById(R.id.tv_description)
        tvWind = root.findViewById(R.id.tv_wind)
        tvPressure = root.findViewById(R.id.tv_pressure)
        tvHumidity = root.findViewById(R.id.tv_humidity)
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
        tvTemp.text = todayWeather.main.temp.toString() + "°"
        tvHumidity.text = todayWeather.main.humidity.toString() + " %"
        tvPressure.text = todayWeather.main.pressure.toString()+" hPa"
        tvWind.text = todayWeather.wind.speed.toString() + " m/s"
        tvDescription.text = todayWeather.weather[0].description
        tvCloud.text = todayWeather.clouds.all.toString()+ " %"
        val imageUrl =
            IMAGE_URL + todayWeather.weather.get(0).icon + "@2x.png"
        Picasso.get().load(imageUrl).into(ivWeather)



    }

    override fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        //pbLoad.visibility = VISIBLE
        if (progressDialog==null){
            try {
                progressDialog = ProgressDialog.show(context, "", "Getting current location");
                progressDialog?.setCancelable(false);
            }
            catch (e:Exception){

            }
        }
    }

    override fun hideLoading() {
        //pbLoad.visibility = INVISIBLE
        if (progressDialog!=null){
            progressDialog?.dismiss();
            progressDialog = null;
        }
    }

}