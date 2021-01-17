package com.example.base_project.detailFim.view;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.base_project.R;
import com.example.base_project.base.BaseFragment;
import com.example.base_project.databinding.FragmentDetailFilmBinding;
import com.example.base_project.detailFim.model.GetDetailResponse;
import com.example.base_project.detailFim.model.GetVideoResponse;
import com.example.base_project.detailFim.viewModel.DetailFilmViewModel;
import com.example.base_project.main.model.Movie;
import com.example.base_project.upcoming.adapter.ListMovieHorizontalAdapter;

import java.util.List;

import butterknife.BindView;

import static com.example.base_project.Utils.KEY_IMAGE;
import static com.example.base_project.Utils.URL_VIDEO;
import static com.example.base_project.main.MainActivity.ID_FILM;

public class DetailFilmFragment extends BaseFragment {
    private FragmentDetailFilmBinding binding;

    private String idFilm = "";
    private DetailFilmViewModel viewModel;
    private ListMovieHorizontalAdapter similarAdapter;
    private ListMovieHorizontalAdapter recommendationAdapter;

    public static DetailFilmFragment getInstance(Bundle bundle) {
        DetailFilmFragment detailFilmFragment = new DetailFilmFragment();
        detailFilmFragment.setArguments(bundle);
        return detailFilmFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_film;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), getLayoutId(), container, false);
        return binding.getRoot();
    }

    @Override
    protected void initFragment() {
        if (getArguments() != null) {
            idFilm = getArguments().getString(ID_FILM);
        }
        initViewModel();
        initView();
    }

    private void initView() {

    }

    private void initViewModel() {
        viewModel = getViewModel(DetailFilmViewModel.class);
        viewModel.initDataViewModel(idFilm);

        viewModel.getGetVideoResponse().observe(this, new Observer<GetVideoResponse>() {
            @Override
            public void onChanged(GetVideoResponse getVideoResponse) {
                if (getVideoResponse != null) {
                    if (getVideoResponse.getResults() != null) {
                        String urlVideo = URL_VIDEO + getVideoResponse.getResults().get(0).getKey();
                        Uri uri = Uri.parse(urlVideo);
                        initViewVideo(uri);

                    }
                }
            }
        });

        viewModel.getGetDetailResponse().observe(this, new Observer<GetDetailResponse>() {
            @Override
            public void onChanged(GetDetailResponse getDetailResponse) {
                initViewGetDetail(getDetailResponse);
            }
        });

        viewModel.getListSimilarMovie().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (similarAdapter == null) {
                    similarAdapter = new ListMovieHorizontalAdapter(getContext(), movies, new ListMovieHorizontalAdapter.ClickItemListener() {
                        @Override
                        public void onClickMovie(int position) {

                        }
                    });
                    binding.rvSuggestFilm.setAdapter(similarAdapter);
                } else {
                    similarAdapter.setListMovie(movies);
                }
            }
        });

        viewModel.getListRecommendations().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (recommendationAdapter == null) {
                    recommendationAdapter = new ListMovieHorizontalAdapter(getContext(), movies, new ListMovieHorizontalAdapter.ClickItemListener() {
                        @Override
                        public void onClickMovie(int position) {

                        }
                    });
                    binding.rvConcernFilm.setAdapter(recommendationAdapter);
                } else {
                    recommendationAdapter.setListMovie(movies);
                }
            }
        });
    }

    private void initViewGetDetail(GetDetailResponse getDetailResponse) {
        binding.tvNameMovie.setText(getDetailResponse.getTitle());
        binding.tvDesc.setText(getDetailResponse.getOverview());
        binding.tvPointNum.setText(getDetailResponse.getVoteAverage().toString());
        binding.tvLikeNum.setText(getDetailResponse.getVoteCount().toString());
        binding.tvDayRelease.setText(getDetailResponse.getReleaseDate());

        String urlLogo = KEY_IMAGE + getDetailResponse.getPosterPath();
        Glide.with(binding.imgLogoFilm).load(urlLogo)
                .into(binding.imgLogoFilm);
    }

    private void initViewVideo(Uri uri) {
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(binding.videoView);
        binding.videoView.setVideoURI(uri);
        binding.videoView.setMediaController(mediaController);
        binding.videoView.requestFocus();
        binding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                binding.progressBar.setVisibility(View.GONE);
                binding.videoView.start();
            }
        });
        binding.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            }
        });
    }

}
