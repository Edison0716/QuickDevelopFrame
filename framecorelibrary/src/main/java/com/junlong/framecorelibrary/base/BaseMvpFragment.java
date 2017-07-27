package com.junlong.framecorelibrary.base;

import android.os.Bundle;
import android.view.View;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/7/7.
 */

public abstract class BaseMvpFragment<V, P extends BasePresenter<V>> extends BaseFragment {
    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter = createPresenter();
        mvpPresenter.attachView((V) this);
    }

    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    protected abstract void showLoading();

    protected abstract void hideLoading();

}
