package com.example.base_project.upcoming.view;

import com.example.base_project.R;
import com.example.base_project.base.BaseFragment;
import com.example.base_project.topRated.view.TopRatedHomeFragment;

public class UpcomingHomeFragment extends BaseFragment {


    public static volatile UpcomingHomeFragment fInstance;

    public static UpcomingHomeFragment getInstance() {
        if (fInstance == null) {
            fInstance = new UpcomingHomeFragment();
        }
        return fInstance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_upcoming;
    }

    @Override
    protected void initFragment() {

    }
}
