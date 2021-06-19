package android.demo.weatherapplication.feature.weather.service

import android.demo.weatherapplication.feature.weather.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface WeatherApi {

    @GET(FETCH_WEATHER)
    @Headers("Content-Type: application/json;")
    suspend fun fetchWeather(@Path("cityCode") cityCode : String) : Response<Weather>

    companion object{
        const val FETCH_WEATHER = "api/obsdata/{cityCode}/c"
    }


}