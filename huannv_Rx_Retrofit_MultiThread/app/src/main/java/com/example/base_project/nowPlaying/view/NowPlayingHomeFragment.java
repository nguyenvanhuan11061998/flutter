package com.example.base_project.nowPlaying.view;

import com.example.base_project.R;
import com.example.base_project.base.BaseFragment;

public class NowPlayingHomeFragment extends BaseFragment {
    public static volatile NowPlayingHomeFragment fInstance;

    public static NowPlayingHomeFragment getInstance() {
        if (fInstance == null) {
            fInstance = new NowPlayingHomeFragment();
        }
        return fInstance;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_now_playing_home;
    }
    @Override
    protected void initFragment() {

    }

}
