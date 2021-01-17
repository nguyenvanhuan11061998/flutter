package com.example.base_project.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.example.base_project.R;

import butterknife.ButterKnife;

public abstract class BaseBindingActivity<BD extends ViewDataBinding> extends AppCompatActivity {
    protected BD binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        ButterKnife.bind(this);
        initAct();
    }

    protected int getLayoutId() {
        return R.layout.layout_container;
    }

    protected abstract void initAct();

    public <VM extends BaseViewModel> VM getViewModel(Class<? extends BaseViewModel> viewModel) {
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        VM vm = (VM) new ViewModelProvider(this, factory).get(viewModel);
        vm.setActivity(this);
        return vm;
    }
}
