package android.demo.weatherapplication.arch.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment

/**
 * Filled from https://stackoverflow.com/a/36340059/726954
 */
open class LoggingFragment : Fragment() {
    private val mLogging: Boolean = true
    override fun onInflate(
        context: Context,
        attrs: AttributeSet,
        savedInstanceState: Bundle?
    ) {
        super.onInflate(context, attrs, savedInstanceState)
        if (mLogging) Log.e(getStandardClassName(this), "onInflate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (mLogging) Log.e(getStandardClassName(this), "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mLogging) Log.e(getStandardClassName(this), "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mLogging) Log.e(getStandardClassName(this), "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        if (mLogging) Log.e(getStandardClassName(this), "onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (mLogging) Log.e(
            getStandardClassName(this),
            "onActivityCreated: " + activity?.javaClass?.simpleName
        )
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (mLogging) Log.e(getStandardClassName(this), "onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        if (mLogging) Log.e(getStandardClassName(this), "onStart")
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (mLogging) Log.e(getStandardClassName(this), "onActivityResult")
    }

    override fun onResume() {
        super.onResume()
        if (mLogging) Log.e(getStandardClassName(this), "onResume")
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        super.onCreateOptionsMenu(menu, inflater)
        if (mLogging) Log.e(getStandardClassName(this), "onCreateOptionsMenu")
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        if (mLogging) Log.e(getStandardClassName(this), "onPrepareOptionsMenu")
    }

    override fun onPause() {
        super.onPause()
        if (mLogging) Log.e(getStandardClassName(this), "onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (mLogging) Log.e(getStandardClassName(this), "onSaveInstanceState")
    }

    override fun onStop() {
        super.onStop()
        if (mLogging) Log.e(getStandardClassName(this), "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (mLogging) Log.e(getStandardClassName(this), "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mLogging) Log.e(getStandardClassName(this), "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        if (mLogging) Log.e(getStandardClassName(this), "onDetach")
    }

    private var loggingName: String? = null
    private fun getStandardClassName(container: LoggingFragment): String? {
        if (loggingName != null) return loggingName
        loggingName = getStandardClassName(container.javaClass)
        return loggingName
    }

    companion object {
        private const val classNameSize = 25
        fun getStandardClassName(container: Class<*>): String {
            return container.simpleName
        }


    }
}