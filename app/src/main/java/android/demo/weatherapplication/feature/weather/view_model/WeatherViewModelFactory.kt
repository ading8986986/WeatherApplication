package android.demo.weatherapplication.feature.weather.view_model

import android.demo.weatherapplication.feature.weather.domain.FetchWeatherUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WeatherViewModelFactory(private val fetchWeatherUseCase: FetchWeatherUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(fetchWeatherUseCase) as T
    }
}