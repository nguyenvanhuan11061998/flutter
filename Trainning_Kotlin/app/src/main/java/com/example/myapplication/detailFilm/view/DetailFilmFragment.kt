package com.example.myapplication.detailFilm.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.example.myapplication.MainActivity.Companion.ID_FILM
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.detailFilm.viewModel.DetailFilmViewModel
import com.example.myapplication.main.adapter.ListMovieHorizontalAdapter

class DetailFilmFragment : BaseFragment(){

    @BindView(R.id.video_view)
    var videoView: VideoView? = null

    @BindView(R.id.progress_bar)
    var progressBar: ProgressBar? = null

    @BindView(R.id.tv_name_movie)
    var nameMovieTextView: TextView? = null

    @BindView(R.id.tv_day_release)
    var dayReleaseTextView: TextView? = null

    @BindView(R.id.tv_like_num)
    var likeNumTextView: TextView? = null

    @BindView(R.id.tv_point_num)
    var pointNumTextView: TextView? = null

    @BindView(R.id.tv_desc)
    var descTextView: TextView? = null

    @BindView(R.id.img_logo_film)
    var logoFilmImageView: ImageView? = null

    @BindView(R.id.rv_suggest_film)
    var suggestFilmView: RecyclerView? = null

    @BindView(R.id.rv_concern_film)
    var concernFilmView: RecyclerView? = null

    var idFilm : String = ""
    var viewModel : DetailFilmViewModel? = null
    var similarAdapter : ListMovieHorizontalAdapter? = null
    var recommendationAdapter : ListMovieHorizontalAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail_film
    }

    fun getInstanc(bundle: Bundle): DetailFilmFragment {
        val detailFilmFragment = DetailFilmFragment()
        detailFilmFragment.arguments = bundle
        return detailFilmFragment
    }

    override fun initFragment() {
        if (arguments != null) {
            idFilm = arguments!!.getString(ID_FILM)!!
        }

        initViewModel()
        initView()

    }

    private fun initView() {

    }

    private fun initViewModel() {
        viewModel = getViewModel(DetailFilmViewModel::class.java)

    }
}