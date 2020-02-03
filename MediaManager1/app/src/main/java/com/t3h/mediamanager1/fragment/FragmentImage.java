package com.t3h.mediamanager1.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.activity.PlayModelActivity;
import com.t3h.mediamanager1.base.BaseAdapter;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentImageBinding;
import com.t3h.mediamanager1.fileStorage.FileStorage;
import com.t3h.mediamanager1.interfaceFragment.ClickFmListener;
import com.t3h.mediamanager1.models.Image;

import java.io.File;
import java.util.ArrayList;

public class FragmentImage extends BaseFragment<FragmentImageBinding> implements MediaListener<Image>,  ValueEventListener, View.OnClickListener, ClickFmListener {
    private boolean checkState;

    private BaseAdapter<Image> adapter;

    private ArrayList<Image> arrImage = new ArrayList<>();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference;
    private FileStorage fileStorage;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initFm();

    }

    private void initFm() {
        checkState = false;

        fileStorage = new FileStorage(getContext());
        adapter = new BaseAdapter<>(getContext(),R.layout.item_image);
        binding.lvPhoto.setAdapter(adapter);
        arrImage = systemData.getImages();

        adapter.setData(arrImage);

        adapter.setListener(this);
        reference = database.getReference("Image");
        reference.addValueEventListener(this);
        binding.setListener(this);

        binding.cbAllImg.setOnClickListener(this);
        notDisplay();
    }

//=================== Xử lý click vào item ========================================================    
    
    @Override
    public void onItemMediaClick(Image image) {

        if (checkState == false){
            Intent intent = new Intent(getContext(), PlayModelActivity.class);
            String data = image.getData();
            intent.putExtra(getParent().EXTRA_DATA_TO_PLAY,data);
            intent.putExtra(getParent().EXTRA_INDEX_FM,1);
            startActivity(intent);
        }else {
            onClickChecked(image);
        }

    }
//long click vào item 
    @Override
    public boolean onItemMediaLongClick(Image image) {

        if (checkState == false){
            for (int i = 0; i < arrImage.size(); i++) {
                arrImage.get(i).setDisplay(View.VISIBLE);
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
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cb_all_img :
                if (binding.cbAllImg.isChecked()){
                    for (Image img: arrImage) {
                        img.setChecked(true);
                    }
                    adapter.notifyDataSetChanged();
                }else {
                    for (Image img: arrImage) {
                        img.setChecked(false);
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void onClickHilder() {
        boolean check = false;

        for (Image img : arrImage) {
            if (img.getChecked()){
                check = true;
            }
        }

        if(check){
            for (Image img : arrImage) {
                if (img.getChecked()){
                    File file = new File(img.getData());
                    fileStorage.moveImgToInternal(file);
                }
            }

            Toast.makeText(getContext(),"Đã dấu ảnh", Toast.LENGTH_LONG).show();
            initFm();
        }else {
            Toast.makeText(getContext(),"Chọn ảnh để dấu",Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override
    public void onClickShare() {
        ArrayList<Uri> arrUriImg = new ArrayList<>();
        for (Image image: arrImage) {
            if (image.getChecked() == true){
                File file = new File(image.getData());
                arrUriImg.add(Uri.fromFile(file));
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

    private void display(){
        binding.llFmImage.setVisibility(View.VISIBLE);
        binding.tvCheckedAllImg.setVisibility(View.VISIBLE);
        binding.cbAllImg.setChecked(false);
        binding.cbAllImg.setVisibility(View.VISIBLE);
    }

    private void notDisplay(){
        binding.llFmImage.setVisibility(View.INVISIBLE);
        binding.tvCheckedAllImg.setVisibility(View.INVISIBLE);
        binding.cbAllImg.setVisibility(View.INVISIBLE);
    }
}
