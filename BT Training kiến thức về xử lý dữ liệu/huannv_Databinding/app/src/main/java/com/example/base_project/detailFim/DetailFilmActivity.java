package com.example.base_project.detailFim;

import android.content.Intent;
import android.os.Bundle;

import com.example.base_project.base.BaseActivity;
import com.example.base_project.detailFim.view.DetailFilmFragment;

import static com.example.base_project.main.MainActivity.ID_FILM;

public class DetailFilmActivity extends BaseActivity {

    @Override
    protected void initAct() {
        String idFilm = "";
        Intent intent = getIntent();
        if (intent != null) {
            idFilm = intent.getStringExtra(ID_FILM);
        }
        Bundle bundle = new Bundle();
        bundle.putString(ID_FILM, idFilm);
        pushView(DetailFilmFragment.getInstance(bundle));
    }
}
