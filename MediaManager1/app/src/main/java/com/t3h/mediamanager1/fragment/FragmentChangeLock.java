package com.t3h.mediamanager1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.activity.LockScreenActivity;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.dao.ShareHelper;
import com.t3h.mediamanager1.databinding.FragmentChangeLockBinding;

import java.util.List;

public class FragmentChangeLock extends BaseFragment<FragmentChangeLockBinding> implements PatternLockViewListener, View.OnClickListener {

    private ShareHelper helper;
    private String curentPattern;
    private boolean check;
    private int index;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_change_lock;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        check = getSettingParent().getCheck();
        initFm();
    }

    private void initFm() {
        curentPattern = null;
        index = 0;
        helper = new ShareHelper(getContext());

        binding.plvChangeLock.addPatternLockListener(this);
        binding.btnChageLockOut.setOnClickListener(this);
        binding.tvChangePassPreventive.setOnClickListener(this);

        if (check == true){
            index = 1;
            setDisplay1();
        }

    }



 // ===============================================================================================
    @Override
    public void onStarted() {

    }

    @Override
    public void onProgress(List<PatternLockView.Dot> progressPattern) {

    }

    @Override
    public void onComplete(List<PatternLockView.Dot> pattern) {

        if (pattern.size() <5){
            Toast.makeText(getContext(),"Mẫu hình quá ngắn, vui lòng vẽ  lại",Toast.LENGTH_SHORT).show();
            return;
        }

        switch (index){
            case 0:
                if (pattern.toString().equals(helper.get(ShareHelper.Keys.PASSWORD,"0"))){
                    index = 1;
                    setDisplay1();
                }else {
                    Toast.makeText(getContext(),"Mẫu hình không chính xác",Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case 1:
                curentPattern = pattern.toString();
                index = 2;
                setDiplay2();
                break;
            case 2:
                helper.set(ShareHelper.Keys.PASSWORD,curentPattern);
                Toast.makeText(getContext(),"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                getSettingParent().finish();
                break;

        }

        binding.plvChangeLock.clearPattern();
    }

    @Override
    public void onCleared() {

    }

 // ==============================================================================================

    private void setDisplay1(){
        binding.tvChangeLock.setText("Nhập mẫu hình mới");
    }

    private void setDiplay2(){
        binding.tvChangeLock.setText("Nhập lại mẫu hình");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_chageLock_out:
                Toast.makeText(getContext(),"Hủy thay đổi",Toast.LENGTH_SHORT).show();
                getSettingParent().finish();
                break;

            case R.id.tv_change_passPreventive:
                Intent intent = new Intent(getContext(), LockScreenActivity.class);
                intent.putExtra(LockScreenActivity.INDEX_TO_LOCK_SCREEN_ACT,2);
                startActivity(intent);
                getSettingParent().finish();
                break;
        }
    }
}
