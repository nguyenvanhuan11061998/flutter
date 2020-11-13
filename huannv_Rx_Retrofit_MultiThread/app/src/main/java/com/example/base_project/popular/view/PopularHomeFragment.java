package com.example.base_project.popular.view;

import com.example.base_project.R;
import com.example.base_project.base.BaseFragment;
import com.example.base_project.nowPlaying.view.NowPlayingHomeFragment;

public class PopularHomeFragment extends BaseFragment {

    public static volatile PopularHomeFragment fInstance;

    public static PopularHomeFragment getInstance() {
        if (fInstance == null) {
            fInstance = new PopularHomeFragment();
        }
        return fInstance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_popular;
    }

    @Override
    protected void initFragment() {

    }
}
