package android.demo.weatherapplication.arch.view_model

/**
 * specifically for livedata of event type
 * **/
class LiveDataEventWrapper<out T>(private val content: T) {
    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun getContent(): T = content
}