package com.t3h.mediamanager1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.activity.MainActivity;
import com.t3h.mediamanager1.activity.SettingActivity;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.dao.ShareHelper;
import com.t3h.mediamanager1.databinding.FragmentChangeLockPreventiveBinding;

public class FragmentChangeLockPreventive extends BaseFragment<FragmentChangeLockPreventiveBinding> implements View.OnClickListener {

    private int index;
    private ShareHelper helper;
    private String passPreventive;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_change_lock_preventive;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initFm();
    }

    private void initFm() {
        helper = new ShareHelper(getContext());
        index = 0;

        binding.tvChangeDrawLock.setOnClickListener(this);
        binding.tvChangeLockPreventiveSucces.setOnClickListener(this);
        binding.tvChangeLockPreventiveContinue.setOnClickListener(this);

        if (helper.get(ShareHelper.Keys.CODE,"-1").equals("-1")){
            index = 1;
            display1();
            binding.tvChangeLockPreventive.setText("Nhập mã dự phòng");
        }
    }



    private void display1(){
        binding.tvChangeLockPreventive.setText("Nhập mã mới");
        binding.edtChangeLockPreventive.setText("");
        binding.tvChangeDrawLock.setVisibility(View.VISIBLE);
    }

    private void display2(){
        binding.edtChangeLockPreventive.setText("");
        binding.tvChangeLockPreventive.setText("Nhập lại mã mới");
        binding.tvChangeLockPreventiveContinue.setVisibility(View.INVISIBLE);
        binding.tvChangeLockPreventiveSucces.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_change_lock_preventive_continue:
                changeLockContinue();
                break;

            case R.id.tv_change_draw_lock:
                    Intent intent = new Intent(getContext(), SettingActivity.class);
                    intent.putExtra(MainActivity.EXTRA_CHANGE_LOCK,2);
                    startActivity(intent);
                    getLockScreenAct().finish();

                break;

            case R.id.tv_change_lock_preventive_succes:

                Log.e(" ====== ",binding.edtChangeLockPreventive.getText().toString());
                Log.e(" ====== ",passPreventive);

                if (binding.edtChangeLockPreventive.getText().toString().equals(passPreventive)){
                    helper.set(ShareHelper.Keys.CODE,passPreventive);
                    Toast.makeText(getContext(),"Đã lưu mã dự phòng",Toast.LENGTH_SHORT).show();
                    getLockScreenAct().finish();
                }else {
                    Toast.makeText(getContext(),"Mã không đúng, nhập lại",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void changeLockContinue() {
        switch (index){
            case 0:
                if (binding.edtChangeLockPreventive.getText().toString().equals(helper.get(ShareHelper.Keys.CODE,"-1"))){
                    index = 1;
                    display1();
                }else {
                    Toast.makeText(getContext(),"Mã hiện tại không đúng",Toast.LENGTH_SHORT).show();
                    return;
                }
                break;

            case 1:
                if (binding.edtChangeLockPreventive.getText().length() > 7){
                    passPreventive = binding.edtChangeLockPreventive.getText().toString();
                    display2();
                }else {
                    Toast.makeText(getContext(),"Mã quá ngắn, nhâp lại",Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }
    }
}
