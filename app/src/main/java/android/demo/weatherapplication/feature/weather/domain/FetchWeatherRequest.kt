package android.demo.weatherapplication.feature.weather.domain

import android.demo.weatherapplication.arch.domain.BaseUseCase
import android.demo.weatherapplication.feature.weather.model.City

data class FetchWeatherRequest(val city: City) : BaseUseCase.UseCaseRequest {

}