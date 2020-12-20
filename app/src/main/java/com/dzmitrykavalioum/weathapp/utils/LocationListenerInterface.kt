package com.dzmitrykavalioum.weathapp.utils

import android.location.Location

interface LocationListenerInterface {
    fun onLocationChanged (location : Location)
}