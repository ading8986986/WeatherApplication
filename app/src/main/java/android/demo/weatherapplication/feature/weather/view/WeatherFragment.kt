package android.demo.weatherapplication.feature.weather.view

import androidx.lifecycle.ViewModelProvider
import android.demo.weatherapplication.R
import android.demo.weatherapplication.arch.fragments.BaseFragment
import android.demo.weatherapplication.arch.net.RetroHttp
import android.demo.weatherapplication.arch.view_model.ViewModelDataWrapper
import android.demo.weatherapplication.databinding.FragmentWeatherBinding
import android.demo.weatherapplication.feature.weather.domain.FetchWeatherUseCase
import android.demo.weatherapplication.feature.weather.model.Weather
import android.demo.weatherapplication.feature.weather.repository.WeatherRepository
import android.demo.weatherapplication.feature.weather.service.WeatherApi
import android.demo.weatherapplication.feature.weather.view_model.WeatherViewModel
import android.demo.weatherapplication.feature.weather.view_model.WeatherViewModelFactory
import android.demo.weatherapplication.common.util.ResourceSingleton
import android.view.View
import androidx.lifecycle.Observer

class WeatherFragment :
    BaseFragment<WeatherViewModel, FragmentWeatherBinding>(WeatherViewModel::class.java) {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_weather
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory? {
        return WeatherViewModelFactory(
            FetchWeatherUseCase(
                (WeatherRepository(
                    RetroHttp.getRetroInstance().createApi(WeatherApi::class.java)
                ))
            )
        )
    }

    override fun bindViewModelObservers() {
        viewBinding.viewModel = viewModel
        viewModel.getWeather().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ViewModelDataWrapper.Status.LOADING -> showLoading()
                ViewModelDataWrapper.Status.ERROR -> showError(it.message)
                ViewModelDataWrapper.Status.SUCCESS -> showWeather(it.data)
            }
        })
    }

    private fun showError(error: String?) {
        viewBinding.apply {
            loading.visibility = View.GONE
            weatherGroup.visibility = View.GONE
            errorHint.visibility = View.VISIBLE
            errorHint.text = error ?: ResourceSingleton.getString(R.string.network_error)
        }
    }

    private fun showWeather(weather: Weather?) {

        viewBinding.apply {
            loading.visibility = View.GONE
            weatherGroup.visibility = View.VISIBLE
            errorHint.visibility = View.GONE
            weather?.let {
                updateTime.text =
                    ResourceSingleton.getString(R.string.last_updated_time).format(it.updateTime)
                weatherIcon.setImageDrawable(
                    ResourceSingleton
                        .getDrawable(it.getWeatherIcon())
                )
                temperature.text = it.temperature
                temperatureIcon.setImageDrawable(
                    ResourceSingleton
                        .getDrawable(it.getTemperatureUnitIcon())
                )
                feelsLikeDegree.text =
                    ResourceSingleton.getString(R.string.feels_like).format(it.feelsLike)
                condition.text = it.weatherCondition
            }
        }

    }

    private fun showLoading() {
        viewBinding.apply {
            loading.visibility = View.VISIBLE
            weatherGroup.visibility = View.GONE
            errorHint.visibility = View.GONE
        }
    }

}