package android.demo.weatherapplication.feature.weather.repository

import android.demo.weatherapplication.R
import android.demo.weatherapplication.arch.domain.DomainDataWrapper
import android.demo.weatherapplication.feature.weather.model.Weather
import android.demo.weatherapplication.feature.weather.service.WeatherApi
import android.demo.weatherapplication.common.util.ResourceSingleton

class WeatherRepository(private val weatherApi: WeatherApi) {
    suspend fun fetchWeatherDetail(cityCode: String): DomainDataWrapper<Weather> {
        val httpResponse = try{
            weatherApi.fetchWeather(cityCode)
        } catch (throwable: Throwable) {
            return DomainDataWrapper.error(message = ResourceSingleton.getString(R.string.network_error),
                throwable = throwable)
        }
        if (httpResponse.isSuccessful) {
            httpResponse.body()?.let {
                return DomainDataWrapper.success(it)
            }
        }
        return DomainDataWrapper.error(message = ResourceSingleton.getString(R.string.network_error))
    }

}