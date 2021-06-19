package android.demo.weatherapplication.arch.view_model

data class ViewModelDataWrapper<out T>(val status: Status, val data: T?, val code: Int?, val throwable: Throwable?, val message: String?) {
    companion object {
        fun <T> success(data: T): ViewModelDataWrapper<T> =
            ViewModelDataWrapper(
                status = Status.SUCCESS,
                data = data,
                code = 0,
                throwable = null,
                message = null
            )
        fun <T> loading(): ViewModelDataWrapper<T> =
            ViewModelDataWrapper(
                status = Status.LOADING,
                data = null,
                code = 0,
                throwable = null,
                message = null
            )
        fun <T> error(code: Int? = 0, throwable: Throwable? = null, message: String? = null): ViewModelDataWrapper<T> =
            ViewModelDataWrapper(
                status = Status.ERROR,
                data = null,
                code = code,
                throwable = throwable,
                message = message
            )
    }

    enum class Status {
        SUCCESS, ERROR, LOADING
    }
}