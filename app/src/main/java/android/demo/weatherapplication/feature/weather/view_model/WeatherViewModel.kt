package android.demo.weatherapplication.feature.weather.view_model

import android.demo.weatherapplication.arch.domain.DomainDataWrapper
import android.demo.weatherapplication.arch.view_model.BaseViewModel
import android.demo.weatherapplication.feature.weather.domain.FetchWeatherRequest
import android.demo.weatherapplication.feature.weather.domain.FetchWeatherUseCase
import android.demo.weatherapplication.feature.weather.model.City
import android.demo.weatherapplication.feature.weather.model.Weather
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.demo.weatherapplication.R
import android.demo.weatherapplication.arch.view_model.ViewModelDataWrapper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers


class WeatherViewModel(private val fetchWeatherUseCase: FetchWeatherUseCase) :
    BaseViewModel() {

    var selectedCity = City.Toronto
    val availableCities = ArrayList<City>()

    private val weather = MutableLiveData<ViewModelDataWrapper<Weather>>()
    fun getWeather(): LiveData<ViewModelDataWrapper<Weather>> {
        return weather
    }

    init {
        availableCities.addAll(City.values())
    }

    override fun refreshData() {
        refreshWeather()
    }

    private fun refreshWeather() {
        // set the init value of weather
        weather.value = ViewModelDataWrapper.loading()
        val fetchWeatherRequest = FetchWeatherRequest(selectedCity)
        viewModelScope.launch {
            val response = fetchWeatherUseCase.executeUseCase(fetchWeatherRequest)
            if (response.status == DomainDataWrapper.Status.SUCCESS) {
                weather.value = ViewModelDataWrapper.success(response.data?.weather!!)
            } else {
                weather.value = ViewModelDataWrapper.error(
                    code = response.code,
                    message = response.message,
                    throwable = response.throwable
                )
            }
        }
    }

    fun getOnItemSelectedListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posiiton: Int, p3: Long) {
                selectedCity = availableCities[posiiton]
                refreshWeather()
            }
        }
    }


    companion object {
        @BindingAdapter("entries")
        @JvmStatic
        fun setSpinnerEntries(spinner: Spinner, cities: ArrayList<City>) {
            val cityNames = Array(cities.size) {
                cities[it].name
            }
            spinner.adapter = ArrayAdapter<String>(
                spinner.context,
                R.layout.support_simple_spinner_dropdown_item,
                cityNames
            )
        }

        @BindingAdapter("onItemSelected")
        @JvmStatic
        fun setOnItemSelected(
            spinner: Spinner,
            onItemSelectedListener: AdapterView.OnItemSelectedListener
        ) {
            spinner.onItemSelectedListener = onItemSelectedListener
        }
    }


}