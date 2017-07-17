package com.junlong.framecorelibrary.base;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/7/7.
 */

public class BasePresenter<V> {
    public V mvpView;

    public void attachView(V mvpView){
        this.mvpView = mvpView;
    }

    public void detachView(){
        this.mvpView = null;
    }
}
