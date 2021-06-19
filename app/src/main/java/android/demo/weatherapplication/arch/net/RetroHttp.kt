package android.demo.weatherapplication.arch.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroHttp private constructor() {

    private val retrofit: Retrofit

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val defaultHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(defaultHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun <T> createApi(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }

    companion object {
        private const val BASE_URL = " https://www.theweathernetwork.com/"
        private val instance = RetroHttp()
        @JvmStatic
        fun getRetroInstance(): RetroHttp {
            return instance
        }
    }
}