package com.junlong.framecorelibrary.rx.rxtools;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.junlong.framecorelibrary.util.ToastUtils.showToast;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/9/13.
 */

public class RxDoubleClick {
    public static void onDoubleClick(View v, final OnEventCallBack callBack) {
        Observable<Object> observable = RxView.clicks(v).share();
        observable.buffer(observable.debounce(400, TimeUnit.MILLISECONDS))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Object>>() {
                    @Override
                    public void accept(@NonNull List<Object> voids) throws Exception {
                        if (voids.size() >= 2) {
                            callBack.doEvent();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
    }
}
