package android.demo.weatherapplication.feature.weather.util

import android.demo.weatherapplication.feature.weather.model.Weather

object ResponseGenerator {

    fun getValidWeather(): Weather {
        return Weather(
            "Fri Jun 18 6:45 AM",
            "Thunderstorm", "11", "19", "19", "C", "CAON0696"
        )
    }
}