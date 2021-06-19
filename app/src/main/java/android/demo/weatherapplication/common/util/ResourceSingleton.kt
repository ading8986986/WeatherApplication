package android.demo.weatherapplication.common.util

import android.demo.weatherapplication.MyApplication
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

object ResourceSingleton {

    fun getString(@StringRes resId: Int): String {
        return MyApplication.getInstance().getString(resId)
    }

    fun getDrawable(@DrawableRes resId: Int) : Drawable?{
        return ContextCompat.getDrawable(MyApplication.getInstance(), resId)
    }
}