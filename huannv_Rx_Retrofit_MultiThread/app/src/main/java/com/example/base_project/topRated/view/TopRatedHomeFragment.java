package com.example.base_project.topRated.view;

import com.example.base_project.R;
import com.example.base_project.base.BaseFragment;
import com.example.base_project.popular.view.PopularHomeFragment;

public class TopRatedHomeFragment extends BaseFragment {

    public static volatile TopRatedHomeFragment fInstance;

    public static TopRatedHomeFragment getInstance() {
        if (fInstance == null) {
            fInstance = new TopRatedHomeFragment();
        }
        return fInstance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_top_rated_home;
    }

    @Override
    protected void initFragment() {

    }
}
