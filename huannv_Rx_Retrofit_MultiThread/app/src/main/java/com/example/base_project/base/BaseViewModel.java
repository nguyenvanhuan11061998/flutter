package com.example.base_project.base;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    protected Activity mActivity;

    public BaseViewModel setActivity(Activity mActivity) {
        this.mActivity = mActivity;
        return this;
    }
}
