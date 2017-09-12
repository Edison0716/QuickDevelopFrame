package com.junlong.framecorelibrary.base;

import android.os.Bundle;

import com.junlong.framecorelibrary.swipebackhelper.SwipeBackHelper;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/7/17.
 */

public abstract class BaseSwipeBackMvcActivity extends BaseActivity {
    protected abstract void showLoading();
    protected abstract void hideLoading();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        initSwipeBack();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }
}
