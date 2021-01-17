package com.example.base_project.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    protected Activity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragment();
    }
    protected abstract int getLayoutId();

    protected abstract void initFragment();

    protected <VM extends BaseViewModel>  VM getViewModel(Class<? extends BaseViewModel> viewModelType) {
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        VM vm = (VM) new ViewModelProvider(this, factory).get(viewModelType);
        vm.setActivity(activity);
        return vm;
    }


    protected void onBackFragment() {
        activity.onBackPressed();
    }

}
