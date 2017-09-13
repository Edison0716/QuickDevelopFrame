package com.junlong.framecorelibrary.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


/**
 * Created by ${巴黎没有摩天轮Li} on 2017/9/13.
 */

public class GlideUtil {

    public static void loadImage(Context context, String url, ImageView view) {
        Glide.with(context).load(url).into(view);
    }

    public static void loadImage(Context context, int path, ImageView view) {
        Glide.with(context).load(path).into(view);
    }

    public static void loadImage(Context context, String url, ImageView view, final RequestCallBack callBack) {
        Glide.with(context).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                callBack.requestFailed();
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                callBack.requestSuccess();
                return false;
            }
        }).into(view);
    }

    public static void loadImage(Context context, int path, ImageView view, final RequestCallBack callBack) {
        Glide.with(context).load(path).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                callBack.requestFailed();
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                callBack.requestSuccess();
                return false;
            }
        }).into(view);
    }

    public static void loadCircleImage(Context context, String url, ImageView view) {
        Glide.with(context).load(url).apply(bitmapTransform(new CropCircleTransformation())).into(view);
    }

    public static void loadCircleImage(Context context, int path, ImageView view) {
        Glide.with(context).load(path).apply(bitmapTransform(new CropCircleTransformation())).into(view);
    }

    public static void loadBlurImage(Context context, String url, ImageView view, int blur) {
        Glide.with(context).load(url).apply(bitmapTransform(new BlurTransformation(blur))).into(view);
    }

    public static void loadBlurImage(Context context, int path, ImageView view, int blur) {
        Glide.with(context).load(path).apply(bitmapTransform(new BlurTransformation(blur))).into(view);
    }

    public static void loadBlurImage(Context context, String url, ImageView view, int blur, final RequestCallBack callBack) {
        Glide.with(context).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                callBack.requestFailed();
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                callBack.requestSuccess();
                return false;
            }
        }).apply(bitmapTransform(new BlurTransformation(blur))).into(view);
    }

    public static void loadBlurImage(Context context, int path, ImageView view, int blur, final RequestCallBack callBack) {
        Glide.with(context).load(path).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                callBack.requestFailed();
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                callBack.requestSuccess();
                return false;
            }
        }).apply(bitmapTransform(new BlurTransformation(blur))).into(view);
    }


}


