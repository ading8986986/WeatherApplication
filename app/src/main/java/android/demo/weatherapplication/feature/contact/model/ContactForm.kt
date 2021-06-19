package android.demo.weatherapplication.feature.contact.model

import android.demo.weatherapplication.BR
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import java.util.regex.Pattern

class ContactForm : BaseObservable() {

    private val contact = Contact()
    private val nameVerifyResult = ContactFieldVerifyResult(true)
    private val emailVerifyResult = ContactFieldVerifyResult(true)
    private val phoneNumberVerifyResult = ContactFieldVerifyResult(true)


    @Bindable
    fun getName(): String {
        return contact.name
    }

    fun setName(value: String) {
        if (contact.name != value) {
            contact.name = value
            notifyPropertyChanged(BR.name)
        }
    }

    @Bindable
    fun getEmail(): String {
        return contact.email
    }

    fun setEmail(value: String) {
        if (contact.email != value) {
            contact.email = value
            notifyPropertyChanged(BR.email)
        }
    }

    @Bindable
    fun getPhoneNumber(): String {
        return contact.phoneNumber
    }

    fun setPhoneNumber(value: String) {
        if (contact.phoneNumber != value) {
            contact.phoneNumber = value
            notifyPropertyChanged(BR.phoneNumber)
        }
    }

    @Bindable
    fun isContactValid(): Boolean {
        return !nameVerifyResult.isError && !emailVerifyResult.isError && !phoneNumberVerifyResult.isError
    }

    fun getContact(): Contact {
        return contact
    }

    fun verifyName(): ContactFieldVerifyResult {
        val pattern = Pattern.compile(".*\\d.*")

        if (contact.name.length < 4) {
            nameVerifyResult.setInvalidResult(NameError.NAME_TOO_SHORT.errorMsg)
        } else if (pattern.matcher(contact.name).matches()) {
            nameVerifyResult.setInvalidResult(NameError.CONTAIN_NUMBERS.errorMsg)
        } else {
            nameVerifyResult.setValidResult()
        }
        notifyPropertyChanged(BR.contactValid)
        return nameVerifyResult
    }

    fun verifyEmail(): ContactFieldVerifyResult {
        val pattern =
            Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
        if (pattern.matcher(getEmail()).matches()) {
            emailVerifyResult.setValidResult()
        } else {
            emailVerifyResult.setInvalidResult(EmailError.WRONG_FORMAT.errorMsg)
        }
        notifyPropertyChanged(BR.contactValid)
        return emailVerifyResult
    }

    fun verifyPhone(): ContactFieldVerifyResult {
        val pattern = Pattern.compile("^([2-9][0-8][0-9][2-9][0-9]{6})")
        if (getPhoneNumber().length == 10 && pattern.matcher(getPhoneNumber()).matches()) {
            phoneNumberVerifyResult.setValidResult()
        } else {
            phoneNumberVerifyResult.setInvalidResult(PhoneNumberError.WRONG_FORMAT.errorMsg)
        }
        notifyPropertyChanged(BR.contactValid)
        return phoneNumberVerifyResult
    }


    companion object {
        fun getGenericValidFieldResult(): ContactFieldVerifyResult {
            return ContactFieldVerifyResult(false)
        }
    }

    enum class NameError(val errorMsg: String) {
        CONTAIN_NUMBERS("Name cannot contain numbers"),
        NAME_TOO_SHORT("Name must be at least 4 characters long")
    }

    enum class EmailError(val errorMsg: String) {
        WRONG_FORMAT("Please input correct format of email"),
    }

    enum class PhoneNumberError(val errorMsg: String) {
        WRONG_FORMAT("Please input correct format of 10 digits phone number"),
    }

}