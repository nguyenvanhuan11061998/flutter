package com.t3h.mediamanager1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.activity.MainActivity;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.dao.ShareHelper;
import com.t3h.mediamanager1.databinding.FragmentForgetPassBinding;

public class FragmentLockForgetPass extends BaseFragment<FragmentForgetPassBinding> implements View.OnClickListener {

    private ShareHelper helper;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_forget_pass;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        helper = new ShareHelper(getContext());
        binding.tvForgetPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_forget_pass:
                String checkText = binding.edtForgetPass.getText().toString();
                String code = helper.get(ShareHelper.Keys.CODE,"-1");
                if (checkText.equals(code)){
                    Toast.makeText(getContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    helper.set(ShareHelper.Keys.CHECKPASS,"true");
                    Intent intent = new Intent(getContext(),MainActivity.class);
                    startActivity(intent);
                    getLockScreenAct().finish();
                }else {
                    Toast.makeText(getContext(),"Sai mã, nhập lại",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
