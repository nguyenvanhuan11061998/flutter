package com.t3h.mediamanager1.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.databinding.ActivityPlayImageBinding;
import com.t3h.mediamanager1.fileStorage.FileStorage;
import com.t3h.mediamanager1.fragment.FragmentMyImg;
import com.t3h.mediamanager1.fragment.FragmentImage;
import com.t3h.mediamanager1.fragment.FragmentImagePlay;
import com.t3h.mediamanager1.fragment.FragmentMyVideo;
import com.t3h.mediamanager1.fragment.FragmentVideo;
import com.t3h.mediamanager1.fragment.FragmentVideoPlay;

import java.io.File;


public class PlayModelActivity extends BaseActivity<ActivityPlayImageBinding> {


    public static final String EXTRA_INDEX_FM = "extra_index_fm";
    public static final int REQUEST_PLAY_MODEL = 2;

    private FragmentImagePlay fmPlayImg = new FragmentImagePlay();
    private FragmentVideoPlay fmPlayVideo = new FragmentVideoPlay();
    private Fragment getCurrentFm;


    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    protected void initAct() {

        binding.musicPlayView1.registerStatePlayAct();

        Intent intent = getIntent();
        int index = intent.getIntExtra(MainActivity.EXTRA_INDEX_FM, 0);
        data = intent.getStringExtra(MainActivity.EXTRA_DATA_TO_PLAY);
        if (index == 1) {
            showFmPlayImg();
        } else if (index == 2) {
            showFmPlayVideo();
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_image;
    }

    public void showFmPlayImg() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel_playImg, fmPlayImg);
        getCurrentFm = fmPlayImg;
        transaction.commitAllowingStateLoss();
    }

    public void showFmPlayVideo() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel_playImg, fmPlayVideo);
        getCurrentFm = fmPlayVideo;
        transaction.commitAllowingStateLoss();
    }

}
