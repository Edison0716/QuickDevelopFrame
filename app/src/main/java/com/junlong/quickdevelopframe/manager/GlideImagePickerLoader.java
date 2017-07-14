package com.junlong.quickdevelopframe.manager;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/7/13.
 */

public class GlideImagePickerLoader implements ImageLoader{
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
       /* Glide.with(activity).load(Uri.fromFile(new File(path)))
                .into(imageView);*/
        Glide.with(activity)
                .load(Uri.fromFile(new File(path)))
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
