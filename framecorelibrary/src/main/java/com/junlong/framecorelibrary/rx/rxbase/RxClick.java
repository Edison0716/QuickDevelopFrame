package com.junlong.framecorelibrary.rx.rxbase;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/5/26.
 * 点击事件防抖动
 */

public class RxClick {
    public static void rxOnClick(View v, int delayTime, final OnClickCallBack callBack) {
        RxView.clicks(v).throttleFirst(delayTime, TimeUnit.SECONDS).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
               callBack.OnClick();
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
