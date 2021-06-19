package android.demo.weatherapplication.arch.fragments

import android.demo.weatherapplication.arch.view_model.BaseViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider


abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>
    (private val mViewModelClass: Class<VM>) : LoggingFragment() {
    open lateinit var viewModel: VM
    open lateinit var viewBinding: DB
    private fun init(inflater: LayoutInflater, container: ViewGroup) {
        viewBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }

    open fun initViewBinding() {}
    @LayoutRes
    abstract fun getLayoutRes(): Int

    //override it if the viewModel has its own factory
    open fun getViewModelFactory():ViewModelProvider.Factory?{
        return null
    }

    private fun getViewM(): VM {
        val viewModelFactory = getViewModelFactory()
        return if (viewModelFactory == null) {
            ViewModelProvider(this).get(mViewModelClass)
        } else {
            ViewModelProvider(this, viewModelFactory).get(mViewModelClass)
        }
    }

    open fun onInject() {}

    /**
     * Init ViewModel in the onCreate
     * **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewM()
    }

    /**
     * Bind View
     * **/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        init(inflater, container!!)
        super.onCreateView(inflater, container, savedInstanceState)
        return viewBinding.root
    }

    /**
     * Bind ViewModel, Observers, Load data
     * **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewBinding()
        bindViewModelObservers()
        refresh()
    }

    abstract fun bindViewModelObservers()


    open fun refresh() {
        viewModel.refreshData()
    }

}