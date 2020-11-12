package com.example.base_project.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.base_project.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        transaction = getSupportFragmentManager().beginTransaction();
        initAct();
    }

    protected int getLayoutId() {
        return R.layout.layout_container;
    }

    protected abstract void initAct();

    protected void pushView(Fragment fragment) {
        transaction.add(R.id.container_frame, fragment);
        transaction.commitAllowingStateLoss();
    }

    public <VM extends BaseViewModel> VM getViewModel(Class<? extends BaseViewModel> viewModel) {
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        VM vm = (VM) new ViewModelProvider(this, factory).get(viewModel);
        vm.setActivity(this);
        return vm;
    }
}
