package com.junlong.framecorelibrary.base;

import android.os.Bundle;

import com.junlong.framecorelibrary.swipebackhelper.SwipeBackHelper;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/7/7.
 */

public abstract class BaseSwipeBackMvpActivity<V, P extends BasePresenter<V>> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        mvpPresenter.attachView((V) this);
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        initSwipeBack();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
        SwipeBackHelper.onDestroy(this);
    }

    protected abstract void showLoading();

    protected abstract void hideLoading();
}
