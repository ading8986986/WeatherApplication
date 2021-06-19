package android.demo.weatherapplication.feature.weather.view_model

import android.demo.weatherapplication.arch.domain.DomainDataWrapper
import android.demo.weatherapplication.feature.weather.domain.FetchWeatherRequest
import android.demo.weatherapplication.feature.weather.domain.FetchWeatherResponse
import android.demo.weatherapplication.feature.weather.domain.FetchWeatherUseCase
import android.demo.weatherapplication.feature.weather.util.ResponseGenerator
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class WeatherViewModelTest {

    lateinit var weatherViewModel: WeatherViewModel
    lateinit var fetchWeatherRequest: FetchWeatherRequest

    lateinit var successResult: DomainDataWrapper<FetchWeatherResponse>
    lateinit var failResult: DomainDataWrapper<FetchWeatherResponse>
    val fetchWeatherUseCase = mock(FetchWeatherUseCase::class.java)


    @Rule
    @JvmField
    var instantExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        successResult =
            DomainDataWrapper.success(FetchWeatherResponse(ResponseGenerator.getValidWeather()))
        failResult = DomainDataWrapper.error()
        weatherViewModel = WeatherViewModel(fetchWeatherUseCase)
        fetchWeatherRequest = FetchWeatherRequest(weatherViewModel.selectedCity)
    }

    @Test
    fun refreshData_Success() = runBlocking {
        fetchWeatherUseCase.stub {
            onBlocking {
                executeUseCase(fetchWeatherRequest)
            }.doReturn(successResult)
        }
        weatherViewModel.refreshData()

        assertNotNull(weatherViewModel.getWeather().value)
        assertNotNull(weatherViewModel.getWeather().value?.data)
        assertEquals(weatherViewModel.getWeather().value?.data, successResult.data?.weather)
        assertTrue(weatherViewModel.getWeather().value?.status == DomainDataWrapper.Status.SUCCESS)
        return@runBlocking
    }

    @Test
    fun getOnItemSelectedListener() {
    }
}