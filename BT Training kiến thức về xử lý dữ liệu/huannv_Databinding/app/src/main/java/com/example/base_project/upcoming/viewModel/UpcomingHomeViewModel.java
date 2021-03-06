package com.example.base_project.upcoming.viewModel;

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

public class UpcomingHomeViewModel extends BaseViewModel {

    private MutableLiveData <List<Movie>> listUpcomingMovie = new MutableLiveData<>();
    private MutableLiveData <List<Movie>> listTopRatedMovie = new MutableLiveData<>();
    private MutableLiveData <List<Movie>> listPopularMovie = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCallApi = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsCallApi() {
        return isCallApi;
    }

    public void setIsCallApi(MutableLiveData<Boolean> isCallApi) {
        this.isCallApi = isCallApi;
    }

    public MutableLiveData<List<Movie>> getListUpcomingMovie() {
        return listUpcomingMovie;
    }

    public void setListUpcomingMovie(MutableLiveData<List<Movie>> listUpcomingMovie) {
        this.listUpcomingMovie = listUpcomingMovie;
    }

    public MutableLiveData<List<Movie>> getListTopRatedMovie() {
        return listTopRatedMovie;
    }

    public void setListTopRatedMovie(MutableLiveData<List<Movie>> listTopRatedMovie) {
        this.listTopRatedMovie = listTopRatedMovie;
    }

    public MutableLiveData<List<Movie>> getListPopularMovie() {
        return listPopularMovie;
    }

    public void setListPopularMovie(MutableLiveData<List<Movie>> listPopularMovie) {
        this.listPopularMovie = listPopularMovie;
    }

    public void initData() {
        getListUpcoming();
        getListPopular();
        getListTopRated();
    }

    private void getListTopRated() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", API_KEY);

        Call<MovieResponse> getListTopRateMovie = ApiUtils.getDataApi().getTopRatedMovies(params);
        getListTopRateMovie.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null && response.body().getListMovie() != null) {
                    listTopRatedMovie.setValue(response.body().getListMovie());
                } else {
                    Toast.makeText(mActivity, mActivity.getString(R.string.he_thong_loi), Toast.LENGTH_SHORT).show();
                }
                isCallApi.setValue(false);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(mActivity, mActivity.getString(R.string.he_thong_loi), Toast.LENGTH_SHORT).show();
                isCallApi.setValue(false);
            }
        });
    }

    private void getListPopular() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", API_KEY);
        Call<MovieResponse> getListPopularMovie = ApiUtils.getDataApi().getPopularMovies(params);
        getListPopularMovie.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null && response.body().getListMovie() != null) {
                    listPopularMovie.setValue(response.body().getListMovie());
                } else {
                    Toast.makeText(mActivity, mActivity.getString(R.string.he_thong_loi), Toast.LENGTH_SHORT).show();
                }
                isCallApi.setValue(false);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(mActivity, mActivity.getString(R.string.he_thong_loi), Toast.LENGTH_SHORT).show();
                isCallApi.setValue(false);
            }
        });
    }

    private void getListUpcoming() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", API_KEY);

        Call<MovieResponse> getListUpcomingMovie = ApiUtils.getDataApi().getUpcomingMovies(params);
        getListUpcomingMovie.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null && response.body().getListMovie() != null) {
                    listUpcomingMovie.setValue(response.body().getListMovie());
                } else {
                    Toast.makeText(mActivity, mActivity.getString(R.string.he_thong_loi), Toast.LENGTH_SHORT).show();
                }
                isCallApi.setValue(false);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(mActivity, mActivity.getString(R.string.he_thong_loi), Toast.LENGTH_SHORT).show();
                isCallApi.setValue(false);
            }
        });
    }
}
