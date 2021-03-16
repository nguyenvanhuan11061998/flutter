package com.example.myapplication.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import butterknife.ButterKnife
import com.example.myapplication.R

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var transaction : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getLayoutId())
        ButterKnife.bind(this)
        transaction = supportFragmentManager.beginTransaction()
        initAct()
    }

    abstract fun initAct()

    fun getLayoutId(): Int {
        return R.layout.layout_container
    }

    protected fun pushView(fragment : Fragment) {
        transaction.add(R.id.container_frame, fragment)
        transaction.commitAllowingStateLoss()
    }

//    fun <VM : BaseViewModel> getViewModel(viewModel : Class<? : BaseViewModel>) : VM{
//        var factory : ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
//        var vm : VM = ViewModelProvider(this, factory).get(viewModel)
//        return vm
//    }

}