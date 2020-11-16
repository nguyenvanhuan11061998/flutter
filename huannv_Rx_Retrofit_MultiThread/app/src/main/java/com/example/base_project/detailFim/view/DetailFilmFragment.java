package com.example.base_project.detailFim.view;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.base_project.R;
import com.example.base_project.base.BaseFragment;
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
    @BindView(R.id.video_view)
    VideoView videoView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.tv_name_movie)
    TextView nameMovieTextView;

    @BindView(R.id.tv_day_release)
    TextView dayReleaseTextView;
    @BindView(R.id.tv_like_num)
    TextView likeNumTextView;
    @BindView(R.id.tv_point_num)
    TextView pointNumTextView;
    @BindView(R.id.tv_desc)
    TextView descTextView;
    @BindView(R.id.img_logo_film)
    ImageView logoFilmImageView;
    @BindView(R.id.rv_suggest_film)
    RecyclerView suggestFilmView;
    @BindView(R.id.rv_concern_film)
    RecyclerView concernFilmView;

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
                    suggestFilmView.setAdapter(similarAdapter);
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
                    concernFilmView.setAdapter(recommendationAdapter);
                } else {
                    recommendationAdapter.setListMovie(movies);
                }
            }
        });
    }

    private void initViewGetDetail(GetDetailResponse getDetailResponse) {
        nameMovieTextView.setText(getDetailResponse.getTitle());
        descTextView.setText(getDetailResponse.getOverview());
        pointNumTextView.setText(getDetailResponse.getVoteAverage().toString());
        likeNumTextView.setText(getDetailResponse.getVoteCount().toString());
        dayReleaseTextView.setText(getDetailResponse.getReleaseDate());

        String urlLogo = KEY_IMAGE + getDetailResponse.getPosterPath();
        Glide.with(logoFilmImageView).load(urlLogo)
                .into(logoFilmImageView);
    }

    private void initViewVideo(Uri uri) {
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(videoView);
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressBar.setVisibility(View.GONE);
                videoView.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            }
        });
    }

}
