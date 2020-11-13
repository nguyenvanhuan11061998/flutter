package com.example.base_project.topRated.view;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.base_project.R;
import com.example.base_project.base.BaseFragment;
import com.example.base_project.main.model.Movie;
import com.example.base_project.popular.view.PopularHomeFragment;
import com.example.base_project.topRated.viewModel.TopRatedHomeViewModel;
import com.example.base_project.upcoming.adapter.ListMovieHorizontalAdapter;
import com.example.base_project.upcoming.viewModel.UpcomingHomeViewModel;

import java.util.List;

import butterknife.BindView;

public class TopRatedHomeFragment extends BaseFragment {

    @BindView(R.id.rv_top_rate)
    RecyclerView topRatedView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    ListMovieHorizontalAdapter topRatedMovieAdapter;
    private TopRatedHomeViewModel viewModel;
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
        viewModel = getViewModel(TopRatedHomeViewModel.class);
        viewModel.initDataViewModel();

        viewModel.getListMovie().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (topRatedMovieAdapter == null) {
                    topRatedMovieAdapter = new ListMovieHorizontalAdapter(getContext(), movies, new ListMovieHorizontalAdapter.ClickItemListener() {
                        @Override
                        public void onClickMovie(int position) {

                        }
                    });
                    topRatedView.setAdapter(topRatedMovieAdapter);
                } else {
                    topRatedMovieAdapter.setListMovie(movies);
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
