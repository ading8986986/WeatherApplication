package android.demo.weatherapplication.feature.weather.domain

import android.demo.weatherapplication.arch.domain.BaseUseCase
import android.demo.weatherapplication.feature.weather.model.Weather

data class FetchWeatherResponse(val weather: Weather) : BaseUseCase.UseCaseResponse {
}