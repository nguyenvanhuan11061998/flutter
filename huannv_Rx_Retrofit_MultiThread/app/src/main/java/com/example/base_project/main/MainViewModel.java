package com.example.base_project.main;

import androidx.lifecycle.MutableLiveData;

import com.example.base_project.base.BaseViewModel;

public class MainViewModel extends BaseViewModel {
    private MutableLiveData<String> title = new MutableLiveData<>();

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public void setTitle(MutableLiveData<String> title) {
        this.title = title;
    }


}
