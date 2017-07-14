package com.junlong.framecorelibrary.rx.rxbase;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/5/31.
 */

public class RxDelayEvent {
    public static void onDelayEvent(int time, final OnEventCallBack callBack) {
        Observable.timer(1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                callBack.doEvent();
            }
        });
    }
}
