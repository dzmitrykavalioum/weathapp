package com.dzmitrykavalioum.weathapp.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.*
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

class GpsLocationHelper {

    val LOCATION_REFRESH_TIME = 3000        // the minimum time to get location update
    val LOCATION_REFRESH_DISTANCE =
        30      // the minimum distance to be changed to get location update
    val PERMISSIONS_REQUEST_LOCATION = 77     // just number for comparing requests

    var myLocationListener: MyLocationListener? = null

    interface MyLocationListener {
        fun onLocationChanged(location: Location)
    }

    fun startListeningUserLocation(context: Context, myListener: MyLocationListener) {
        myLocationListener = myListener

        val myLocationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager

        val mLocationListener = object : LocationListener {
            override fun onLocationChanged(p0: Location?) {
                if (p0 != null) {
                    myLocationListener!!.onLocationChanged(p0)
                } else {
                    Log.d("GPSLocationHelper", "location is null")
                }
            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

            }

            override fun onProviderEnabled(p0: String?) {

            }

            override fun onProviderDisabled(p0: String?) {

            }
        }
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            myLocationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                LOCATION_REFRESH_TIME.toLong(),
                LOCATION_REFRESH_DISTANCE.toFloat(),
                mLocationListener
            )
        } else {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                PERMISSIONS_REQUEST_LOCATION
            )
        }

    }

    fun getCityNameByLocation(context: Context, latitude: Double, longitude: Double): String {
        var CityName: String = ""
        var geocoder: Geocoder = Geocoder(context, Locale.ENGLISH)
        var addesses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)
        if (addesses.size >= 0) {
            Log.d("GpsLocationHelper", addesses.get(0).locality)
        }
        else{
            Log.d("GpsLocationHelper", "addresses is empty")

        }
        return CityName
    }

}