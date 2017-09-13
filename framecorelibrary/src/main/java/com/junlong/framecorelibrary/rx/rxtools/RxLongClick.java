package com.junlong.framecorelibrary.rx.rxtools;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/9/13.
 */

public class RxLongClick {
    public static void onLongClick(View v, final OnEventCallBack callBack) {
        RxView.longClicks(v)
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        callBack.doEvent();
                    }
                });
    }
}
