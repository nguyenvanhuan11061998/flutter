package com.t3h.mediamanager1.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentInforAppBinding;

public class FragmentInformation extends BaseFragment<FragmentInforAppBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_infor_app;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFm();
    }

    private void initFm() {
        binding.tvOutFmInfor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_out_fmInfor:
                getParent().showFragment(getParent().getFmStart());
                break;
        }
    }
}
