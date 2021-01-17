package com.example.base_project;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import static com.example.base_project.Utils.KEY_IMAGE;

public class AppBinding {

    @BindingAdapter("image")
    public static void setImage (ImageView imageView, String image) {
        String linkImage = KEY_IMAGE + image;
        Glide.with(imageView).load(linkImage).into(imageView);
    }
}
