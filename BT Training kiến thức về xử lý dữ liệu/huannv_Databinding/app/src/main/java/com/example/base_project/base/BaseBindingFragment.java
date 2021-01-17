package com.example.base_project.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseBindingFragment<BD extends ViewDataBinding> extends Fragment {
    protected Activity activity;
    protected BD binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(activity), getLayoutId(), container, false);
        return binding.getRoot();
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
}
