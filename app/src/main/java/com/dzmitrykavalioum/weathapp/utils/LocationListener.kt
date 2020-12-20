package com.dzmitrykavalioum.weathapp.utils

import android.location.Location
import android.location.LocationListener
import android.os.Bundle

open class LocationListener : LocationListener {

    lateinit var locationListenerInterface:LocationListenerInterface

    override fun onLocationChanged(p0: Location?) {
        TODO("Not yet implemented")
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onProviderEnabled(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onProviderDisabled(p0: String?) {
        TODO("Not yet implemented")
    }
}