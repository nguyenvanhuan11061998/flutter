package com.t3h.mediamanager1.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.Toast;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.activity.PlayModelActivity;
import com.t3h.mediamanager1.base.BaseAdapter;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentVideoBinding;
import com.t3h.mediamanager1.fileStorage.FileStorage;
import com.t3h.mediamanager1.interfaceFragment.ClickFmListener;
import com.t3h.mediamanager1.models.Video;

import java.io.File;
import java.util.ArrayList;


public class FragmentVideo extends BaseFragment<FragmentVideoBinding> implements MediaListener<Video>,  View.OnClickListener, ClickFmListener {

    private BaseAdapter<Video> adapter;

    private ArrayList<Video> arrVideo;
    private FileStorage fileStorage;
    private boolean checkState;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFm();

    }

    private void initFm() {

        checkState = false;
        fileStorage = new FileStorage(getContext());
        adapter = new BaseAdapter<>(getContext(),R.layout.item_video);
        binding.lvVideo.setAdapter(adapter);

        arrVideo = systemData.getVideo();

        adapter.setData(arrVideo);
        adapter.setListener(this);
        binding.setListener(this);

        binding.cbAllVid.setOnClickListener(this);
        notDisplay();
    }

    @Override
    public void onItemMediaClick(Video video) {
        if (checkState == false){
            String data = video.getData();
            Intent intent = new Intent(getContext(), PlayModelActivity.class);
            intent.putExtra(getParent().EXTRA_DATA_TO_PLAY,data);
            intent.putExtra(getParent().EXTRA_INDEX_FM,2);
            startActivity(intent);
        }else {
            onClickChecked(video);
        }
    }

    @Override
    public boolean onItemMediaLongClick(Video video) {

        if (checkState == false){
            for (int i = 0; i < arrVideo.size(); i++) {
                arrVideo.get(i).setDisplay(View.VISIBLE);
                adapter.notifyItemChanged(i);
            }
            checkState = true;
            display();
        }else {
            initFm();
        }
        return true;
    }

    @Override
    public void onClickChecked(Video video) {
        if (video.getChecked() == false){
            video.setChecked(true);
        } else {
            video.setChecked(false);
        }
        adapter.notifyItemChanged(arrVideo.indexOf(video));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cb_all_vid:
                if (binding.cbAllVid.isChecked()){
                    for (Video video: arrVideo) {
                        video.setChecked(true);
                    }
                } else {
                    for (Video video: arrVideo) {
                        video.setChecked(false);
                    }
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onClickHilder() {
        boolean check = false;
        for (Video video : arrVideo) {
            if (video.getChecked()){
                check = true;
            }
        }
        if(check){

            for (Video video : arrVideo) {
                if (video.getChecked()){
                    File file = new File(video.getData());
                    fileStorage.moveVideoToInternal(file);
                }
            }
            Toast.makeText(getContext(),"Đã dấu video", Toast.LENGTH_LONG).show();
            initFm();
        }else {
            Toast.makeText(getContext(),"Chọn video để dấu",Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override

    public void onClickShare() {
        ArrayList<Uri> arrUriVideo = new ArrayList<>();
        for (Video video: arrVideo) {
            if (video.getChecked() == true){
                File file = new File(video.getData());
                arrUriVideo.add(Uri.fromFile(file));
            }
        }
        if (arrUriVideo.size() > 10){
            Toast.makeText(getContext(),"Vui lòng chọn số lượng video <10 dể đảm bảo tốc dộ tải ", Toast.LENGTH_SHORT).show();
        }else if (arrUriVideo.size() <=10 && arrUriVideo.size() > 0){
            fileStorage.shareVideo(arrUriVideo);
        }else {
            Toast.makeText(getContext(),"Chọn vìdeo để chia sẻ",Toast.LENGTH_SHORT).show();
        }
        initFm();
    }

    private void display(){
        binding.cbAllVid.setVisibility(View.VISIBLE);
        binding.llFmVideo.setVisibility(View.VISIBLE);
        binding.tvCheckedAllVideo.setVisibility(View.VISIBLE);
    }

    private void notDisplay(){
        binding.cbAllVid.setVisibility(View.INVISIBLE);
        binding.llFmVideo.setVisibility(View.INVISIBLE);
        binding.tvCheckedAllVideo.setVisibility(View.INVISIBLE);
    }

}
