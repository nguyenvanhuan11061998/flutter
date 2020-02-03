package com.t3h.mediamanager1.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.Utils.ValidatorUtils;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.dao.ShareHelper;
import com.t3h.mediamanager1.databinding.ActivityRegisterBinding;

import java.util.List;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> implements View.OnClickListener, PatternLockViewListener {
    public ShareHelper helper;
    private String curentPattern ;
    @Override
    protected void initAct() {
        helper = new ShareHelper(this);
        curentPattern = null;

        setDisplay1();
        binding.tvPrev.setOnClickListener(this);
        binding.pattenLockView.addPatternLockListener(this);;



    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_Prev:
                binding.pattenLockView.removePatternLockListener(this);
                initAct();
        }
    }



 // ========================== PattenLockView ======================================================

    @Override
    public void onStarted() {
        Log.e(" ===== "," start ");
    }

    @Override
    public void onProgress(List<PatternLockView.Dot> progressPattern) {

    }

    @Override
    public void onComplete(List<PatternLockView.Dot> pattern) {
        if (pattern.size() < 5){
            Toast.makeText(this,"Độ bảo mật thấp, vui lòng tạo lại",Toast.LENGTH_SHORT).show();
            binding.pattenLockView.clearPattern();
            return;
        }else {
            if (curentPattern == null){
                curentPattern = pattern.toString();
                binding.pattenLockView.clearPattern();
                setDisplay2();
            }else {
                if (pattern.toString().equals(curentPattern)){
                    helper.set(ShareHelper.Keys.PASSWORD,curentPattern);
                    Toast.makeText(this,"Đã lưu mẫu hình ",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(this,"Mẫu hình không đúng , vẽ lại",Toast.LENGTH_SHORT).show();
                    binding.pattenLockView.clearPattern();
                }
            }
        }
    }

    @Override
    public void onCleared() {
    }


 // ================================================================================================

    private void setDisplay1(){
        binding.tv1HintPass.setText("Vẽ mẫu hình tạo khóa bảo mật cho ứng dụng của bạn");
        binding.llActRegister.setVisibility(View.INVISIBLE);
    }

    private void setDisplay2(){
        binding.tv1HintPass.setText("Xác nhận khóa bảo mật");
        binding.llActRegister.setVisibility(View.VISIBLE);
    }
}
