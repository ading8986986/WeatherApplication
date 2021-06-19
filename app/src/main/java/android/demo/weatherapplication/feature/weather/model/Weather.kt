package android.demo.weatherapplication.feature.weather.model

import android.demo.weatherapplication.R
import android.text.TextUtils
import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class Weather(
    @SerializedName("updatetime")
    val updateTime: String,
    @SerializedName("wxcondition")
    val weatherCondition: String,
    val icon: String,
    val temperature: String,
    @SerializedName("feels_like")
    val feelsLike: String,
    @SerializedName("temperature_unit")
    val temperatureUnit: String,
    @SerializedName("placecode")
    val placeCode: String
    ) {

    @DrawableRes
    fun getWeatherIcon():  Int {
        val randomIconNumber = Random.nextInt()
        return when (randomIconNumber % 3) {
            0 -> R.drawable.ic_wi_cloudy
            1 -> R.drawable.ic_wi_day_hail
            2 -> R.drawable.ic_wi_day_rain
            else -> R.drawable.ic_wi_day_rain
        }
    }

    @DrawableRes
    fun getTemperatureUnitIcon(): Int {
        return if (CelsiusUnit.equals(temperatureUnit, true)) {
            R.drawable.ic_wi_celsius
        } else {
            R.drawable.ic_wi_fahrenheit
        }
    }

    fun getFeelsLikeTemperature(): String {
        return "Feels like $feelsLike"
    }

    companion object {
        private const val CelsiusUnit: String = "C"
        private const val FahrenheitUnit = "F"

    }
}
