package com.t3h.mediamanager1.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import android.view.View;
import android.widget.Toast;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.Utils.ScanFile;
import com.t3h.mediamanager1.activity.MainActivity;
import com.t3h.mediamanager1.activity.PlayModelActivity;
import com.t3h.mediamanager1.base.BaseAdapter;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentYourImgBinding;
import com.t3h.mediamanager1.dialog.DeleteDialog;
import com.t3h.mediamanager1.fileStorage.FileStorage;
import com.t3h.mediamanager1.interfaceFragment.MyFmListener;
import com.t3h.mediamanager1.models.Image;

import java.io.File;
import java.util.ArrayList;

public class FragmentMyImg extends BaseFragment<FragmentYourImgBinding> implements MediaListener<Image>, View.OnClickListener, MyFmListener, DeleteDialog.calbackDialog {

    private FileStorage fileStorage;
    private BaseAdapter<Image> adapter;
    private ArrayList<Image> arrImage = new ArrayList<>();

    private boolean checkState;
    private DeleteDialog deleteDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_your_img;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFm();
    }

    private void initFm() {

        checkState = false;
        fileStorage = new FileStorage(getContext());

        arrImage = fileStorage.getImage();

        adapter = new BaseAdapter<>(getContext(),R.layout.item_image);
        binding.lvPhoto1.setAdapter(adapter);
        adapter.setData(arrImage);

        binding.setListener(this);
        adapter.setListener(this);
        binding.cbAllImgOfFd.setOnClickListener(this);

        notDisplay();
        deleteDialog = new DeleteDialog(getContext());
        deleteDialog.setCalback(this);

    }

    @Override
    public void onItemMediaClick(Image image) {
        if (checkState == false){
            Intent intent = new Intent(getContext(), PlayModelActivity.class);
            intent.putExtra(MainActivity.EXTRA_DATA_TO_PLAY,image.getData());
            intent.putExtra(MainActivity.EXTRA_INDEX_FM,1);
            startActivity(intent);
        }else {
            onClickChecked(image);
        }
    }

    @Override
    public boolean onItemMediaLongClick(Image image) {

        if (checkState == false){
            for (Image img:arrImage) {
                img.setDisplay(View.VISIBLE);
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
    public void onClickChecked(Image image) {
        int index = arrImage.indexOf(image);
        boolean checked = arrImage.get(index).getChecked();
        if (checked){
            arrImage.get(index).setChecked(false);
        }else {
            arrImage.get(index).setChecked(true);
        }
        adapter.notifyItemChanged(index);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cb_all_img_of_fd:
                if (binding.cbAllImgOfFd.isChecked()){
                    for (Image img: arrImage) {
                        img.setChecked(true);
                    }
                }else {
                    for (Image img: arrImage) {
                        img.setChecked(false);
                    }
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onClickCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getMyModelAct().takePhoto(intent);
        initFm();
    }

    @Override
    public void onClickDelete() {
        ArrayList<Image> deleteArr = new ArrayList<>();

        for (Image image: arrImage){
            if (image.getChecked()){
                deleteArr.add(image);
            }
        }
        if (deleteArr.size() == 0){
            Toast.makeText(getContext(),"Chọn ảnh muốn xóa",Toast.LENGTH_SHORT).show();
            initFm();
        }else {
            deleteDialog.setTextDialog(" "+deleteArr.size()+" ảnh sẽ bị xóa !");
            deleteDialog.show();
        }
    }

    @Override
    public void onClickDlDelete() {
        for (Image image: arrImage){
            if (image.getChecked()){
                File file = new File(image.getData());
                file.delete();
            }
        }
        Toast.makeText(getContext(),"Ảnh đã được xóa",Toast.LENGTH_SHORT).show();
        initFm();
    }

    @Override
    public void onClickRestore() {
        boolean check = false;

        for (Image img: arrImage) {
            if (img.getChecked()){
                File file = new File(img.getData());
                fileStorage.moveImgtoExternal(file);
                check = true;
            }
        }

        ScanFile scanFile = new ScanFile(getContext());
        scanFile.scanfile();

        if (check){
            Toast.makeText(getContext(),"Đã bỏ dấu ảnh",Toast.LENGTH_LONG).show();
            initFm();
        }else {
            Toast.makeText(getContext(),"Vui lòng chọn ảnh để restore",Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override
    public void onClickShare() {
        ArrayList<Uri> arrUriImg = new ArrayList<>();
        for (Image image: arrImage) {
            if (image.getChecked() == true){
                File newFile = new File(image.getData());
                Uri contentUri = FileProvider.getUriForFile(getContext(),"com.mydomain.fileprovider",newFile);
                arrUriImg.add(contentUri);
            }
        }

        if (arrUriImg.size() > 100){
            Toast.makeText(getContext(),"Vui lòng chọn số lượng ảnh < 100 dể đảm bảo tốc dộ tải ", Toast.LENGTH_SHORT).show();
        }else if (arrUriImg.size()<=100 && arrUriImg.size() > 0){
            fileStorage.shareImage(arrUriImg);
        }else {
            Toast.makeText(getContext(),"Chọn ảnh để chia sẻ",Toast.LENGTH_SHORT).show();
        }
        initFm();
    }

    private void notDisplay(){
        binding.LLMyImg.setVisibility(View.INVISIBLE);
        binding.tvCheckedAllImgFd.setVisibility(View.INVISIBLE);
        binding.cbAllImgOfFd.setVisibility(View.INVISIBLE);
    }

    private void display(){
        binding.LLMyImg.setVisibility(View.VISIBLE);
        binding.tvCheckedAllImgFd.setVisibility(View.VISIBLE);
        binding.cbAllImgOfFd.setVisibility(View.VISIBLE);
    }

}
