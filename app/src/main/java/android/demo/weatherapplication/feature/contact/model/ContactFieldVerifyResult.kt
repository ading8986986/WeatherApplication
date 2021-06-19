package android.demo.weatherapplication.feature.contact.model

data class ContactFieldVerifyResult(var isError: Boolean, var errorMsg: String = "") {
    fun setInvalidResult(errorMsg: String = "") {
        isError = true
        this.errorMsg = errorMsg
    }

    fun setValidResult() {
        isError = false
        errorMsg = ""
    }
}