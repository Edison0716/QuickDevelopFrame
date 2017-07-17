package com.junlong.framecorelibrary.base;

import android.os.Bundle;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/7/7.
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    public void showLoading() {

    }

    public void hideLoading() {

    }
}
