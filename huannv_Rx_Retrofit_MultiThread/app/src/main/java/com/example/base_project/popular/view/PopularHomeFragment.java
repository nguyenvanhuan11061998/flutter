package com.example.base_project.popular.view;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.base_project.R;
import com.example.base_project.base.BaseFragment;
import com.example.base_project.main.model.Movie;
import com.example.base_project.nowPlaying.view.NowPlayingHomeFragment;
import com.example.base_project.popular.viewModel.PopularHomeViewModel;
import com.example.base_project.topRated.viewModel.TopRatedHomeViewModel;
import com.example.base_project.upcoming.adapter.ListMovieHorizontalAdapter;

import org.checkerframework.checker.units.qual.C;

import java.util.List;

import butterknife.BindView;

public class PopularHomeFragment extends BaseFragment {
    @BindView(R.id.rv_popular)
    RecyclerView popularView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    ListMovieHorizontalAdapter popularMovieAdapter;

    public static volatile PopularHomeFragment fInstance;

    private PopularHomeViewModel viewModel;

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
        viewModel = getViewModel(PopularHomeViewModel.class);
        viewModel.initDataViewModel();

        viewModel.getListPopularMovie().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (popularMovieAdapter == null) {
                    popularMovieAdapter = new ListMovieHorizontalAdapter(getContext(), movies, new ListMovieHorizontalAdapter.ClickItemListener() {
                        @Override
                        public void onClickMovie(int position) {

                        }
                    });
                    popularView.setAdapter(popularMovieAdapter);
                } else {
                    popularMovieAdapter.setListMovie(movies);
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
