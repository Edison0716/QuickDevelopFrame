package com.junlong.framecorelibrary.rx.rxtools;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/7/11.
 */

public interface OnEditCallBack<T> {
    void doEvent(T t);
    T doRequest(CharSequence charSequence);
}
