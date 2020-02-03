package com.t3h.mediamanager1.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseAdapter;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentMusicBinding;
import com.t3h.mediamanager1.models.Music;

import java.util.ArrayList;

public class FragmentMusic extends BaseFragment<FragmentMusicBinding> implements MediaListener<Music> {

    private BaseAdapter<Music> adapter;
    ArrayList<Music> arrMusic;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_music;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new BaseAdapter<>(getContext(),R.layout.item_music);
        binding.lvMusic.setAdapter(adapter);

        arrMusic = systemData.getMusic();

        adapter.setData(arrMusic);
        adapter.setListener(this);
    }

    @Override
    public void onItemMediaClick(Music music) {
        app.getService().setArrMusic(adapter.getData());
        int index = adapter.getData().indexOf(music);
        app.getService().create(index);
    }

    @Override
    public boolean onItemMediaLongClick(Music music) {
        return false;
    }

    @Override
    public void onClickChecked(Music music) {

    }
}
