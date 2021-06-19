package android.demo.weatherapplication.feature.contact

import android.demo.weatherapplication.R
import android.demo.weatherapplication.arch.fragments.BaseFragment
import android.demo.weatherapplication.arch.view_model.ViewModelDataWrapper
import android.demo.weatherapplication.databinding.FragmentContactBinding
import android.widget.Toast
import androidx.lifecycle.observe

class ContactFragment :
    BaseFragment<ContactViewModel, FragmentContactBinding>(ContactViewModel::class.java) {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_contact
    }

    override fun bindViewModelObservers() {
        viewBinding.viewmodel = viewModel

        viewModel.nameVerifyResult.observe(viewLifecycleOwner) {
            viewBinding.nameWrapper.isErrorEnabled = it.isError
            viewBinding.nameWrapper.error = it.errorMsg
        }

        viewModel.emailVerifyResult.observe(viewLifecycleOwner) {
            viewBinding.emailWrapper.isErrorEnabled = it.isError
            viewBinding.emailWrapper.error = it.errorMsg
        }

        viewModel.phoneNumberVerifyResult.observe(viewLifecycleOwner) {
            viewBinding.phoneWrapper.isErrorEnabled = it.isError
            viewBinding.phoneWrapper.error = it.errorMsg
        }

        viewModel.submitViewModelWrapper.observe(viewLifecycleOwner) {
            if (it.status == ViewModelDataWrapper.Status.SUCCESS) {
                val contactName = it.data?.name ?: ""
                Toast.makeText(
                    this.context,
                    "Submit as $contactName successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}