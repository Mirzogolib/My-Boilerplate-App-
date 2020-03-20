package com.example.myboilerplateapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myboilerplateapp.App

abstract class BaseFragment : Fragment()  {
    abstract var layoutRes: Int
    private var instanceStateSaved: Boolean = false
    companion object {
        private const val STATE_LAUNCH_FLAG = "state_launch_flag"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    open fun onBackPressed() {}

    protected fun isFirstLaunch(savedInstanceState: Bundle?): Boolean {
        val savedAppCode = savedInstanceState?.getString(STATE_LAUNCH_FLAG)
        return savedAppCode != App.appCode
    }

    override fun onResume() {
        super.onResume()
        instanceStateSaved = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        instanceStateSaved = true
        outState.putString(STATE_LAUNCH_FLAG, App.appCode)
    }



    fun isRealRemoving(): Boolean =
        (isRemoving && !instanceStateSaved) ||
                ((parentFragment as? BaseFragment)?.isRealRemoving() ?: false)

}