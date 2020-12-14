package com.dzmitrykavalioum.weathapp.ui.todayweather

import InfoWeather
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.dzmitrykavalioum.weathapp.R
import com.dzmitrykavalioum.weathapp.utils.Constants.Companion.APP_ID
import com.dzmitrykavalioum.weathapp.utils.Constants.Companion.METRIC
import com.google.android.gms.location.*
import com.squareup.picasso.Picasso
import java.lang.IllegalStateException

class TodayWeatherFragment : Fragment(), TodayWeatherContract.ViewContract {

    private var PERMISSION_ID = 716
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    var presenter: TodayWeatherPresenter?= null
    private lateinit var todayWeatherViewModel: TodayWeatherViewModel
    private lateinit var tvTemp: TextView
    private lateinit var tvCity: TextView
    private lateinit var tvHumi: TextView
    private lateinit var pb_load:ProgressBar
    private lateinit var iv_weather:ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_today_weather, container, false)


        presenter  = TodayWeatherPresenter(this)
        tvCity = root.findViewById(R.id.tv_city)
        tvTemp = root.findViewById(R.id.tv_temp)
        tvHumi = root.findViewById(R.id.tv_humidity)
        pb_load = root.findViewById(R.id.pb_load)
        iv_weather = root.findViewById(R.id.iv_weather)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        getLastLocation()
//        getNewLocation()
        //presenter.getTodayWeather("london", "metric", APP_ID)
//        presenter.getTodayWeatherByLonLat(latitude,longitude, "metric", APP_ID)

        return root
    }

    override fun showTodayWeather(todayWeather: InfoWeather) {
        tvCity.setText(todayWeather.name.toString())
        tvTemp.setText(todayWeather.main.temp.toString())
        tvHumi.setText(todayWeather.main.humidity.toString())
        val imageUrl = "http://openweathermap.org/img/wn/"+todayWeather.weather.get(0).icon+"@2x.png"
        Picasso.get().load(imageUrl).into(iv_weather)


    }

    override fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
       pb_load.visibility = VISIBLE
    }

    override fun hideLoading() {
        pb_load.visibility = INVISIBLE

    }

    private fun checkPermission(): Boolean {
        if (activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            } == PackageManager.PERMISSION_GRANTED ||
            activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }


    private fun requestPermission() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                PERMISSION_ID
            )
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun getLastLocation() {

        if (checkPermission()) {
            if (isLocationEnabled()) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        getNewLocation()
                        //Toast.makeText(activity,"Your location is disabled ", Toast.LENGTH_LONG).show()

                    } else {

                        latitude = location.latitude
                        longitude = location.longitude
                        presenter?.getTodayWeatherByLonLat(latitude,longitude, "metric", APP_ID)
//                        Toast.makeText(
//                            activity,
//                            "Your location is: \t lat: " + location.latitude.toString() + " lon: " + location.longitude.toString(),
//                            Toast.LENGTH_LONG
//                        ).show()
                    }
                }
            } else {
                Toast.makeText(activity, "Please enable you location service", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            requestPermission()
        }


    }

    private fun getNewLocation() {
        showLoading()
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )
        hideLoading()
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation = p0.lastLocation

            try {
                latitude = lastLocation.latitude
                longitude = lastLocation.longitude
                presenter?.getTodayWeatherByLonLat(latitude,longitude, METRIC, APP_ID)
//                Toast.makeText(
//                    requireContext(),
//                    "Your location is: \t lat: " + lastLocation.latitude.toString() + " lon: " + lastLocation.longitude.toString(),
//                    Toast.LENGTH_LONG
//                ).show()
            }
            catch (e:IllegalStateException){
                Log.d("Exception" , "illegal state exception")
            }


        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Permission", "You have the permission")
            }
        }
    }
}