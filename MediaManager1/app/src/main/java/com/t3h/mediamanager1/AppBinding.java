package com.t3h.mediamanager1;

import androidx.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppBinding {

    @BindingAdapter("thumb")
    public static void setThumb(ImageView im, String img){
        Glide.with(im)
                .load(img)
                .error(R.drawable.ic_music)
                .into(im);
    }

    public static ImageView getThumb(ImageView im, String img){
        Glide.with(im)
                .load(img)
                .error(R.drawable.failed_img)
                .into(im);

        return im;
    }

    @BindingAdapter("time")
    public static void setTime(TextView tv, int time){                                              //luôn để static
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        tv.setText(format.format(new Date(time)));
    }

}
