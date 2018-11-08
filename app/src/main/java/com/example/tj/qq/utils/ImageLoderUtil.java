package com.example.tj.qq.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoderUtil {
    public static void load(Context context, String url, int placeHolder, int error, ImageView view){
        Glide.with(context)//
                .load(url)//
                .apply(new RequestOptions()//
                        .diskCacheStrategy(DiskCacheStrategy.NONE)//
                        .skipMemoryCache(true)//
                        .placeholder(placeHolder)//
                        .error(error))//
                .into(view);
    }

}
