package com.t3h.mediamanager1.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.activity.MainActivity;
import com.t3h.mediamanager1.activity.PlayModelActivity;
import com.t3h.mediamanager1.base.BaseAdapter;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentYourVideoFdBinding;
import com.t3h.mediamanager1.dialog.DeleteDialog;
import com.t3h.mediamanager1.fileStorage.FileStorage;
import com.t3h.mediamanager1.interfaceFragment.MyFmListener;
import com.t3h.mediamanager1.models.Video;

import java.io.File;
import java.util.ArrayList;

public class FragmentMyVideo extends BaseFragment<FragmentYourVideoFdBinding> implements MediaListener<Video>,View.OnClickListener, MyFmListener, DeleteDialog.calbackDialog {

    private BaseAdapter<Video> adapter;
    private ArrayList<Video> arrVideo;
    private FileStorage fileStorage;
    private boolean checkState;

    private DeleteDialog deleteDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_your_video_fd;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initFm();

    }

    private void initFm() {

        checkState = false;
        fileStorage = new FileStorage(getContext());

        arrVideo = fileStorage.getVideo();

        adapter = new BaseAdapter<>(getContext(),R.layout.item_video);
        binding.lvVideo1.setAdapter(adapter);
        adapter.setData(arrVideo);

        binding.setListener(this);
        adapter.setListener(this);
        binding.cbAllVideoOfFd.setOnClickListener(this);

        notDisplay();

        deleteDialog = new DeleteDialog(getContext());
        deleteDialog.setCalback(this);
    }

    @Override
    public void onItemMediaClick(Video video) {
        if (checkState == false){
            Intent intent = new Intent(getContext(), PlayModelActivity.class);
            intent.putExtra(MainActivity.EXTRA_DATA_TO_PLAY,video.getData());
            intent.putExtra(MainActivity.EXTRA_INDEX_FM,2);
            startActivity(intent);
        }else {
            onClickChecked(video);
        }
    }

    @Override
    public boolean onItemMediaLongClick(Video video) {
        if (checkState == false){
            for (Video video1 : arrVideo) {
                video1.setDisplay(View.VISIBLE);
            }
            adapter.notifyDataSetChanged();

            display();
            checkState = true;
        }else {
            initFm();
        }
        return true;
    }

    @Override
    public void onClickChecked(Video video) {
        if (video.getChecked()){
            video.setChecked(false);
        }else {
            video.setChecked(true);
        }
        adapter.notifyItemChanged(arrVideo.indexOf(video));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cb_all_video_of_fd:
                if (binding.cbAllVideoOfFd.isChecked()){
                    for (Video video :arrVideo) {
                        video.setChecked(true);
                    }
                }else {
                    for (Video video: arrVideo) {
                        video.setChecked(false);
                    }
                }
                adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClickCamera() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        getMyModelAct().makeVideo(intent);

        initFm();


    }

    @Override
    public void onClickDelete() {
        ArrayList<Video> deleteArr = new ArrayList<>();

        for (Video video: arrVideo){
            if (video.getChecked()){
                deleteArr.add(video);
            }
        }
        if (deleteArr.size() == 0){
            Toast.makeText(getContext(),"Chọn video muốn xóa",Toast.LENGTH_SHORT).show();
            initFm();
        }else {
            deleteDialog.setTextDialog(" "+deleteArr.size()+" video sẽ bị xóa !");
            deleteDialog.show();
        }
    }

    @Override
    public void onClickDlDelete() {
        for (Video video: arrVideo){
            if (video.getChecked()){
                File file = new File(video.getData());
                file.delete();
            }
        }
        Toast.makeText(getContext(),"Video đã được xóa",Toast.LENGTH_SHORT).show();
        initFm();
    }

    @Override
    public void onClickRestore() {
        boolean check = false;

        for (Video video: arrVideo) {
            if (video.getChecked()){
                File file = new File(video.getData());
                fileStorage.moveVideotoExternal(file);
                check = true;
            }
        }

        if (check){
            Toast.makeText(getContext(),"Đã bỏ dấu video",Toast.LENGTH_LONG).show();
            initFm();
        }else {
            Toast.makeText(getContext(),"Vui lòng chọn video để bỏ dấu",Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override
    public void onClickShare() {
        ArrayList<Uri> arrUriVideo = new ArrayList<>();
        for (Video video: arrVideo) {
            if (video.getChecked() == true){
                File newFile = new File(video.getData());
                Uri contentUri = FileProvider.getUriForFile(getContext(),"com.mydomain.fileprovider",newFile);
                arrUriVideo.add(contentUri);
            }
        }
        if (arrUriVideo.size() > 10){
            Toast.makeText(getContext(),"Vui lòng chọn số lượng video <10 dể đảm bảo tốc dộ tải ", Toast.LENGTH_SHORT).show();
        }else if (arrUriVideo.size() <=10 && arrUriVideo.size() > 0){
            fileStorage.shareVideo(arrUriVideo);
        }else {
            Toast.makeText(getContext(),"Chọn vìdeo để chia sẻ",Toast.LENGTH_SHORT).show();
        }
        boolean check = false;
        initFm();
    }

    private void display(){
        binding.tvCheckedAllVdOfFd.setVisibility(View.VISIBLE);
        binding.cbAllVideoOfFd.setVisibility(View.VISIBLE);
        binding.LLMyVideo.setVisibility(View.VISIBLE);
    }

    private void notDisplay(){
        binding.tvCheckedAllVdOfFd.setVisibility(View.INVISIBLE);
        binding.cbAllVideoOfFd.setVisibility(View.INVISIBLE);
        binding.LLMyVideo.setVisibility(View.INVISIBLE);
    }
}
