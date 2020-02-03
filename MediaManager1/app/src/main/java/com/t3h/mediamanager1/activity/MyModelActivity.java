package com.t3h.mediamanager1.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentTransaction;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.databinding.ActivityMyModelBinding;
import com.t3h.mediamanager1.fileStorage.FileStorage;
import com.t3h.mediamanager1.fragment.FragmentMyImg;
import com.t3h.mediamanager1.fragment.FragmentMyVideo;

import java.io.File;

public class MyModelActivity extends BaseActivity<ActivityMyModelBinding> {

    private FragmentMyImg fmMyImg = new FragmentMyImg();
    private FragmentMyVideo fmMyVideo = new FragmentMyVideo();
    public static final int REQUEST_TAKE_PHOTO = 10;
    public static final int REQUEST_MAKE_VIDEO = 11;


    @Override
    protected void initAct() {
        Intent intent = getIntent();
        int index = intent.getIntExtra(MainActivity.EXTRA_INDEX_FM, 0);

        if (index == 3){
            showFmMyImg();
        }else if (index == 4){
            showFmMyVideo();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_model;
    }

    private void showFmMyImg() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel3, fmMyImg);
        transaction.commitAllowingStateLoss();
    }

    private void showFmMyVideo() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel3, fmMyVideo);
        transaction.commitAllowingStateLoss();
    }

    public void takePhoto(Intent intent) {
        FileStorage fileStorage = new FileStorage(this);
        File file = fileStorage.createImage();

        Uri uri = FileProvider.getUriForFile(this, "com.mydomain.fileprovider", file);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }

    public void makeVideo(Intent intent) {
        if (intent.resolveActivity(getPackageManager()) != null) {
            FileStorage fileStorage = new FileStorage(this);
            File file = fileStorage.createVideo();

            Uri uri = FileProvider.getUriForFile(this, "com.mydomain.fileprovider", file);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, REQUEST_MAKE_VIDEO);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_TAKE_PHOTO) {

            Toast.makeText(this, " Ảnh mới được tạo", Toast.LENGTH_LONG).show();

            fmMyImg = new FragmentMyImg();
            showFmMyImg();

        } else if (requestCode == REQUEST_MAKE_VIDEO && resultCode == RESULT_OK) {

            Toast.makeText(this, " Video đang được tạo", Toast.LENGTH_LONG).show();

            fmMyVideo = new FragmentMyVideo();
            showFmMyVideo();
        }
    }
}
