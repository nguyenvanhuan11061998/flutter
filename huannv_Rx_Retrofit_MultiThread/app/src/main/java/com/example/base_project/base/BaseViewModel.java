package com.example.base_project.base;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

class BaseViewModel extends ViewModel {
    protected Activity mActivity;

    public BaseViewModel setActivity(Activity mActivity) {
        this.mActivity = mActivity;
        return this;
    }
}
