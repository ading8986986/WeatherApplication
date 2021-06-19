package android.demo.weatherapplication

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    companion object {
        private lateinit var mInstance: MyApplication
        fun getInstance(): MyApplication {
            return mInstance
        }
    }

}