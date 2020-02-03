package com.t3h.mediamanager1.activity;

import android.content.Intent;

import androidx.fragment.app.FragmentTransaction;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.databinding.ActivityLockScreenBinding;
import com.t3h.mediamanager1.fragment.FragmentChangeLockPreventive;
import com.t3h.mediamanager1.fragment.FragmentLockForgetPass;

public class LockScreenActivity extends BaseActivity<ActivityLockScreenBinding> {

    public static final String INDEX_TO_LOCK_SCREEN_ACT = "extra_index_to_Lock_screen_act";
    private FragmentLockForgetPass fmForgetPass = new FragmentLockForgetPass();
    private FragmentChangeLockPreventive fmChangeLockPreventive = new FragmentChangeLockPreventive();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lock_screen;
    }

    @Override
    protected void initAct() {
        Intent intent = getIntent();
        int index = intent.getIntExtra(INDEX_TO_LOCK_SCREEN_ACT,0);
        if (index == 1){
            showFmforgetPass();
        }else if (index == 2){
            showFmChangeLockPreventive();
        }
    }

    private void showFmChangeLockPreventive() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel2,fmChangeLockPreventive);
        transaction.commitAllowingStateLoss();
    }

    public void showFmforgetPass(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel2,fmForgetPass);
        transaction.commitAllowingStateLoss();
    }




}
