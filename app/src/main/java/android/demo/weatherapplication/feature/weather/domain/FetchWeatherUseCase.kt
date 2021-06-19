package android.demo.weatherapplication.feature.weather.domain

import android.demo.weatherapplication.arch.domain.BaseUseCase
import android.demo.weatherapplication.arch.domain.DomainDataWrapper
import android.demo.weatherapplication.feature.weather.repository.WeatherRepository

class FetchWeatherUseCase(private val weatherRepository: WeatherRepository) :
    BaseUseCase<FetchWeatherRequest, FetchWeatherResponse>() {
    override suspend fun executeUseCase(request: FetchWeatherRequest?): DomainDataWrapper<FetchWeatherResponse> {
        val weather = weatherRepository.fetchWeatherDetail(request?.city?.cityCode?:"")
        return if (weather.status == DomainDataWrapper.Status.SUCCESS) {
            DomainDataWrapper.success(FetchWeatherResponse(weather.data!!))
        } else {
            DomainDataWrapper.error(
                code = weather.code,
                message = weather.message,
                throwable = weather.throwable
            )
        }
    }
}