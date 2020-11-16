package com.example.base_project.upcoming.view;

import android.content.Intent;
import android.widget.RelativeLayout;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.base_project.R;
import com.example.base_project.base.BaseFragment;
import com.example.base_project.detailFim.DetailFilmActivity;
import com.example.base_project.main.MainActivity;
import com.example.base_project.main.model.Movie;
import com.example.base_project.upcoming.adapter.ListMovieHorizontalAdapter;
import com.example.base_project.upcoming.viewModel.UpcomingHomeViewModel;

import java.util.List;

import butterknife.BindView;

import static com.example.base_project.main.MainActivity.ID_FILM;

public class UpcomingHomeFragment extends BaseFragment {
    @BindView(R.id.rv_top_rate)
    RecyclerView topRatedView;
    @BindView(R.id.rv_popular)
    RecyclerView popularView;
    @BindView(R.id.rv_upcoming)
    RecyclerView upcomingView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    ListMovieHorizontalAdapter upcomingMovingAdapter;
    ListMovieHorizontalAdapter topRatedMovieAdapter;
    ListMovieHorizontalAdapter popularMovieAdapter;

    private UpcomingHomeViewModel viewModel;

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
        viewModel = getViewModel(UpcomingHomeViewModel.class);
        viewModel.initData();

        viewModel.getListUpcomingMovie().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (upcomingMovingAdapter == null) {
                    upcomingMovingAdapter = new ListMovieHorizontalAdapter(getContext(), movies, new ListMovieHorizontalAdapter.ClickItemListener() {
                        @Override
                        public void onClickMovie(int position) {
                            Intent intent = new Intent(getActivity(), DetailFilmActivity.class);
                            intent.putExtra(ID_FILM, upcomingMovingAdapter.getListMovie().get(position).getId().toString());
                            startActivity(intent);
                        }
                    });
                    upcomingView.setAdapter(upcomingMovingAdapter);
                } else {
                    upcomingMovingAdapter.setListMovie(movies);
                }
            }
        });

        viewModel.getListPopularMovie().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (popularMovieAdapter == null) {
                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    popularMovieAdapter = new ListMovieHorizontalAdapter(getContext(), movies, new ListMovieHorizontalAdapter.ClickItemListener() {
                        @Override
                        public void onClickMovie(int position) {
                            Intent intent = new Intent(getActivity(), DetailFilmActivity.class);
                            intent.putExtra(ID_FILM, popularMovieAdapter.getListMovie().get(position).getId().toString());
                            startActivity(intent);
                        }
                    });
                    popularView.setLayoutManager(layoutManager);
                    popularView.setAdapter(popularMovieAdapter);
                } else {
                    popularMovieAdapter.setListMovie(movies);
                }
            }
        });

        viewModel.getListTopRatedMovie().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (topRatedMovieAdapter == null) {
                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    topRatedMovieAdapter = new ListMovieHorizontalAdapter(getContext(), movies, new ListMovieHorizontalAdapter.ClickItemListener() {
                        @Override
                        public void onClickMovie(int position) {
                            Intent intent = new Intent(getActivity(), DetailFilmActivity.class);
                            intent.putExtra(ID_FILM, topRatedMovieAdapter.getListMovie().get(position).getId().toString());
                            startActivity(intent);
                        }
                    });
                    topRatedView.setLayoutManager(layoutManager);
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
