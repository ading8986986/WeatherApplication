package android.demo.weatherapplication.feature.contact

import android.demo.weatherapplication.arch.view_model.BaseViewModel
import android.demo.weatherapplication.arch.view_model.ViewModelDataWrapper
import android.demo.weatherapplication.feature.contact.model.Contact
import android.demo.weatherapplication.feature.contact.model.ContactForm
import android.demo.weatherapplication.feature.contact.model.ContactFieldVerifyResult
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ContactViewModel : BaseViewModel() {

    val contactForm = ContactForm()

    private val _nameVerifyResult = MutableLiveData<ContactFieldVerifyResult>()
    val nameVerifyResult: LiveData<ContactFieldVerifyResult> = _nameVerifyResult

    private val _emailVerifyResult = MutableLiveData<ContactFieldVerifyResult>()
    val emailVerifyResult: LiveData<ContactFieldVerifyResult> = _emailVerifyResult

    private val _phoneNumberVerifyResult = MutableLiveData<ContactFieldVerifyResult>()
    val phoneNumberVerifyResult: LiveData<ContactFieldVerifyResult> = _phoneNumberVerifyResult

    private val _submitResult = MutableLiveData<ViewModelDataWrapper<Contact>>()
    val submitViewModelWrapper: LiveData<ViewModelDataWrapper<Contact>> = _submitResult


    fun getNameOnFocusChangeListener(): OnFocusChangeListener {
        return OnFocusChangeListener { view: View, focused: Boolean ->
            val et = view as EditText
            if (et.editableText.toString().isNotEmpty() && !focused) {
                _nameVerifyResult.value = contactForm.verifyName()
            }
        }
    }


    fun getNameOnTextChangeListener(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                _nameVerifyResult.value = ContactForm.getGenericValidFieldResult()
            }
        }
    }

    fun getEmailOnFocusChangeListener(): OnFocusChangeListener? {
        return OnFocusChangeListener { view: View, focused: Boolean ->
            val et = view as EditText
            if (et.editableText.toString().isNotEmpty() && !focused) {
                _emailVerifyResult.value = contactForm.verifyEmail()
            }
        }
    }

    fun getEmailOnTextChangeListener(): TextWatcher? {
        return object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                _emailVerifyResult.value = ContactForm.getGenericValidFieldResult()
            }
        }
    }

    fun getPhoneNumberOnFocusChangeListener(): OnFocusChangeListener? {
        return OnFocusChangeListener { view: View, focused: Boolean ->
            val et = view as EditText
            if (et.editableText.toString().isNotEmpty() && !focused) {
                _phoneNumberVerifyResult.value = contactForm.verifyPhone()
            }
        }
    }

    fun getPhoneNumberOnTextChangeListener(): TextWatcher? {
        return object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                _phoneNumberVerifyResult.value = ContactForm.getGenericValidFieldResult()
            }
        }
    }


    fun getSubmitOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            _nameVerifyResult.value = contactForm.verifyName()
            _emailVerifyResult.value = contactForm.verifyEmail()
            _phoneNumberVerifyResult.value = contactForm.verifyPhone()
            if (_nameVerifyResult.value?.isError == true ||
                _emailVerifyResult.value?.isError == true ||
                _phoneNumberVerifyResult.value?.isError == true
            ) {
                return@OnClickListener
            } else {
                _submitResult.value = ViewModelDataWrapper.success(contactForm.getContact())
            }
        }
    }

    fun getEditorActionListener(): TextView.OnEditorActionListener {
        return TextView.OnEditorActionListener { view, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE && contactForm.getPhoneNumber()
                    .isNotEmpty()
            ) {
                _phoneNumberVerifyResult.value = contactForm.verifyPhone()
            }
            false
        }
    }


    companion object {
        @BindingAdapter("onFocus")
        @JvmStatic
        fun bindFocusChange(
            editText: EditText,
            onFocusChangeListener: OnFocusChangeListener
        ) {
            if (editText.onFocusChangeListener == null) {
                editText.onFocusChangeListener = onFocusChangeListener
            }
        }

        @BindingAdapter("onTextChange")
        @JvmStatic
        fun bindTextWatcherChange(
            editText: EditText,
            textWatcher: TextWatcher
        ) {
            editText.addTextChangedListener(textWatcher)
        }

        @BindingAdapter("onEditorAction")
        @JvmStatic
        fun setOnEditorAction(
            editText: EditText,
            onEditorActionListener: TextView.OnEditorActionListener
        ) {
            editText.setOnEditorActionListener(onEditorActionListener)
        }
    }

    override fun refreshData() {
    }


}

