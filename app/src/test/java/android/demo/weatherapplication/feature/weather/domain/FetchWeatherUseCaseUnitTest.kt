package android.demo.weatherapplication.feature.weather.domain

import android.demo.weatherapplication.arch.domain.DomainDataWrapper
import android.demo.weatherapplication.feature.weather.model.City
import android.demo.weatherapplication.feature.weather.model.Weather
import android.demo.weatherapplication.feature.weather.repository.WeatherRepository
import android.demo.weatherapplication.feature.weather.util.ResponseGenerator
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class FetchWeatherUseCaseUnitTest {

    private val fetchWeatherRequest = FetchWeatherRequest(City.Toronto)
    private lateinit var fetchWeatherUseCase: FetchWeatherUseCase
    private val repo = mock(WeatherRepository::class.java)

    private lateinit var successResult: DomainDataWrapper<Weather>
    private lateinit var failResult : DomainDataWrapper<Weather>

    @Before
    fun setUp() {
        successResult = DomainDataWrapper.success(ResponseGenerator.getValidWeather())
        failResult = DomainDataWrapper.error()
        fetchWeatherUseCase = FetchWeatherUseCase(repo)
    }

    @Test
    fun fetchWeather_Success() = runBlocking {
            repo.stub {
                onBlocking {
                    fetchWeatherDetail(fetchWeatherRequest.city.cityCode)
                }.doReturn(successResult)
            }
            val response = fetchWeatherUseCase.executeUseCase(fetchWeatherRequest)
            verify(repo).fetchWeatherDetail(fetchWeatherRequest.city.cityCode)
            assertNotNull(response)
            assertNotNull(response.data)
            assertEquals(response.data?.weather, successResult.data)
            assertTrue(response.status == DomainDataWrapper.Status.SUCCESS)
        return@runBlocking
    }

    @Test
    fun fetchWeather_Fail() = runBlocking {
        repo.stub {
            onBlocking {
                fetchWeatherDetail(fetchWeatherRequest.city.cityCode)
            }.doReturn(failResult)
        }
        val response = fetchWeatherUseCase.executeUseCase(fetchWeatherRequest)
        assertNotNull(response)
        assertNull(response.data)
        assertTrue(response.status == DomainDataWrapper.Status.ERROR)
        assertEquals(response.message, failResult.message)
        assertEquals(response.code, failResult.code)
        assertEquals(response.throwable, failResult.throwable)

        return@runBlocking
    }

}