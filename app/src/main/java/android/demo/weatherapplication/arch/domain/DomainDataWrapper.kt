package android.demo.weatherapplication.arch.domain

data class DomainDataWrapper<out T>(val status: Status, val data: T?, val code: Int?, val throwable: Throwable?, val message: String?) {
    companion object {
        fun <T> success(data: T): DomainDataWrapper<T> =
            DomainDataWrapper(
                status = Status.SUCCESS,
                data = data,
                code = 0,
                throwable = null,
                message = null
            )

        fun <T> error(code: Int? = 0, throwable: Throwable? = null, message: String? = null): DomainDataWrapper<T> =
            DomainDataWrapper(
                status = Status.ERROR,
                data = null,
                code = code,
                throwable = throwable,
                message = message
            )
    }

    enum class Status {
        SUCCESS, ERROR
    }
}