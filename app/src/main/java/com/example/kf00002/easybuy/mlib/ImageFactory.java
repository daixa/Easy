package com.example.kf00002.easybuy.mlib;

import android.content.Context;
import android.widget.ImageView;

import com.example.kf00002.easybuy.R;
import com.squareup.picasso.Picasso;

/**
 * ImageView创建工厂
 * Created by KF00001 on 2016/2/29.
 */
public class ImageFactory {

    /**
     * 获取ImageView视图的同时加载显示url
     *
     * @param context
     * @param url
     * @return
     */
    public static ImageView getImageView(Context context, String url) {
        ImageView imageView = new ImageView(context);
        Picasso.with(context).load(url).placeholder(R.drawable.icon_empty).error(R.drawable.icon_error).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
}
