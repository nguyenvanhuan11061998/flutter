package com.example.base_project.nowPlaying.view;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.base_project.R;
import com.example.base_project.base.BaseFragment;
import com.example.base_project.main.model.Movie;
import com.example.base_project.nowPlaying.viewModel.NowPlayingHomeViewModel;
import com.example.base_project.popular.viewModel.PopularHomeViewModel;
import com.example.base_project.upcoming.adapter.ListMovieHorizontalAdapter;

import java.util.List;

import butterknife.BindView;

public class NowPlayingHomeFragment extends BaseFragment {
    @BindView(R.id.rv_now_playing)
    RecyclerView nowPlayingView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private NowPlayingHomeViewModel viewModel;
    ListMovieHorizontalAdapter nowPlayingMovieAdapter;

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
        initViewModel();
        initView();
    }

    private void initView() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initViewModel();
                viewModel.getIsCallApi().setValue(true);
            }
        });
    }

    private void initViewModel() {
        viewModel = getViewModel(NowPlayingHomeViewModel.class);
        viewModel.initDataViewModel();

        viewModel.getlistNowPlayingMovie().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (nowPlayingMovieAdapter == null) {
                    nowPlayingMovieAdapter = new ListMovieHorizontalAdapter(getContext(), movies, new ListMovieHorizontalAdapter.ClickItemListener() {
                        @Override
                        public void onClickMovie(int position) {

                        }
                    });
                    nowPlayingView.setAdapter(nowPlayingMovieAdapter);
                } else {
                    nowPlayingMovieAdapter.setListMovie(movies);
                }
            }
        });

        viewModel.getIsCallApi().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isCallApi) {
                if (!isCallApi) {
                    dismissRefresh();
                }
            }
        });
    }

    public void dismissRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

}
