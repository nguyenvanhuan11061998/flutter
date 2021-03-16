package com.example.myapplication.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import butterknife.ButterKnife

abstract class BaseFragment : Fragment() {
    protected lateinit var activity : Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = getActivity()!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView : View = inflater.inflate(getLayoutId(), container, false)
        ButterKnife.bind(this, rootView)
        return rootView
    }

    abstract fun getLayoutId(): Int

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initFragment()
    }

    abstract fun initFragment()

    protected fun onBackFragment() {
        activity.onBackPressed()
    }

    protected fun <VM : BaseViewModel?> getViewModel(viewModelType: Class<in BaseViewModel?>?): VM {
        val factory: ViewModelProvider.Factory = NewInstanceFactory()
        val vm = ViewModelProvider(this, factory).get(viewModelType) as VM
        vm!!.setActivity(activity)
        return vm
    }
}