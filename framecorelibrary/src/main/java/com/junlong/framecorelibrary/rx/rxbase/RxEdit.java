package com.junlong.framecorelibrary.rx.rxbase;

import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/7/11.
 * 动态输入搜索
 */

public class RxEdit {
    public static void rxEditEvent(EditText v, final OnEditCallBack callBack) {
        RxTextView.textChanges(v)
                //当你敲完字之后停下来的半秒就会执行下面语句
                .debounce(500, TimeUnit.MILLISECONDS)
                //下面这两个都是数据转换
                //flatMap：当同时多个网络请求访问的时候，前面的网络数据会覆盖后面的网络数据
                //switchMap：当同时多个网络请求访问的时候，会以最后一个发送请求为准，前面网路数据会被最后一个覆盖
                .switchMap(new Function<CharSequence, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull CharSequence charSequence) throws Exception {
                        return Observable.just(callBack.doRequest(charSequence));
                    }
                }).compose(RxSchedulers.io_main_observable())

                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object strings) throws Exception {
                        //界面更新，这里用打印替代
                        callBack.doEvent(strings);
                    }
                });
    }
}
