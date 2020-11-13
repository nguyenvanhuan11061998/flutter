package com.example.base_project.nowPlaying.viewModel;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.base_project.R;
import com.example.base_project.base.BaseViewModel;
import com.example.base_project.base.baseApi.ApiUtils;
import com.example.base_project.main.model.Movie;
import com.example.base_project.upcoming.model.MovieResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.base_project.Utils.API_KEY;

public class NowPlayingHomeViewModel extends BaseViewModel {
    private MutableLiveData<List<Movie>> listNowPlayingMovie = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCallApi = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsCallApi() {
        return isCallApi;
    }

    public void setIsCallApi(MutableLiveData<Boolean> isCallApi) {
        this.isCallApi = isCallApi;
    }

    public MutableLiveData<List<Movie>> getlistNowPlayingMovie() {
        return listNowPlayingMovie;
    }

    public void setlistNowPlayingMovie(MutableLiveData<List<Movie>> listNowPlayingMovie) {
        this.listNowPlayingMovie = listNowPlayingMovie;
    }

    public void initDataViewModel() {
        getListPopular();
    }

    private void getListPopular() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", API_KEY);

        Call<MovieResponse> getlistNowPlayingMovie = ApiUtils.getDataApi().getNowPlayingMovies(params);
        getlistNowPlayingMovie.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                isCallApi.setValue(false);
                if (response.body() != null && response.body().getListMovie() != null) {
                    listNowPlayingMovie.setValue(response.body().getListMovie());
                } else {
                    Toast.makeText(mActivity, mActivity.getString(R.string.he_thong_loi), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                isCallApi.setValue(false);
                Toast.makeText(mActivity, mActivity.getString(R.string.he_thong_loi), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
