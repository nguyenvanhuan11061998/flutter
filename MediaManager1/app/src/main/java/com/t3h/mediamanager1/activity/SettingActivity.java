package com.t3h.mediamanager1.activity;

import android.content.Intent;

import androidx.fragment.app.FragmentTransaction;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.databinding.ActivitySettingBinding;
import com.t3h.mediamanager1.fragment.FragmentChangeLock;

public class SettingActivity extends BaseActivity<ActivitySettingBinding> {

    private FragmentChangeLock fmChangeLock = new FragmentChangeLock();
    private boolean check = false;

    public boolean getCheck() {
        return check;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }


        @Override
    protected void initAct() {
        Intent intent = getIntent();
        int checkFm = intent.getIntExtra(MainActivity.EXTRA_CHANGE_LOCK,0);
        if (checkFm == 1){
            showFmChangeLock();
        }else if (checkFm == 2){
            check = true;
            showFmChangeLock();
        }
    }

    private void showFmChangeLock(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel1, fmChangeLock);
        transaction.commitAllowingStateLoss();
    }



}
