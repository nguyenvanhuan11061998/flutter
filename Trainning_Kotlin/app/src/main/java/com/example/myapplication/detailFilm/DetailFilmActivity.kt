package com.example.myapplication.detailFilm

import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.myapplication.MainActivity.Companion.ID_FILM
import com.example.myapplication.base.BaseActivity

class DetailFilmActivity : BaseActivity() {

    override fun initAct() {
        var idFilm : String = ""
        var intent : Intent = getIntent()
        idFilm = intent.getStringExtra(ID_FILM)
        var bundle : Bundle = Bundle()
        bundle.putString(ID_FILM, idFilm)
//        pushView()
    }
}