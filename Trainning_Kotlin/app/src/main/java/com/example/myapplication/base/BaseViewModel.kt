package com.example.myapplication.base

import android.app.Activity
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(){
    protected lateinit var activity : Activity

    fun setActivity(activity : Activity) : BaseViewModel {
        this.activity = activity
        return this
    }
}