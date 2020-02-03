package com.t3h.mediamanager1.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.dao.ShareHelper;
import com.t3h.mediamanager1.databinding.ActivityLoginBinding;

import java.util.List;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements  PatternLockViewListener, View.OnClickListener {


    private ShareHelper helper;
    private int countFailed = 0;

    @Override
    protected void initAct() {

        binding.loginPatternLockView.removePatternLockListener(this);

        countFailed = 0;
        helper = new ShareHelper(this);
        binding.loginPatternLockView.addPatternLockListener(this);
        binding.loginPatternLockView.setTactileFeedbackEnabled(true);
        binding.loginPatternLockView.setInputEnabled(true);
        binding.tvLoginFailed.setVisibility(View.INVISIBLE);
        binding.tvLoginForgetPass.setOnClickListener(this);
        checkFailedPass();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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

        if (pattern.toString().equals(helper.get(ShareHelper.Keys.PASSWORD,"0"))){

            Intent intent = new Intent(this,MainActivity.class);
            Toast.makeText(this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }else {
            countFailed = countFailed +1;
            Toast.makeText(this,"Nhập khóa không chính xác",Toast.LENGTH_LONG).show();
            if (countFailed >= 5){
                helper.set(ShareHelper.Keys.CHECKPASS,"false");
            }
            checkFailedPass();
        }

        binding.loginPatternLockView.clearPattern();
    }

    private void checkFailedPass() {
        if (helper.get(ShareHelper.Keys.CHECKPASS,"0").equals("false")){

            binding.loginPatternLockView.setInputEnabled(false);
            binding.tvLoginFailed.setVisibility(View.VISIBLE);

            Handler handler = new Handler();                                                                  //tạo một handler để chạy SplashActivity
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    helper.set(ShareHelper.Keys.CHECKPASS,"true");
                    initAct();
                }
            },300000);
        }
    }

    @Override
    public void onCleared() {

    }

    // ==============================================================================================
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login_forget_pass:
                Intent intent = new Intent(this,LockScreenActivity.class);
                intent.putExtra(LockScreenActivity.INDEX_TO_LOCK_SCREEN_ACT,1);
                startActivity(intent);
                break;
        }
    }

}
