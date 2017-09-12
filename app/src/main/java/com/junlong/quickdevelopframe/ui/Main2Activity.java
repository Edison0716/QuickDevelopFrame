package com.junlong.quickdevelopframe.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.junlong.framecorelibrary.base.BaseMvcActivity;
import com.junlong.quickdevelopframe.R;

public class Main2Activity extends BaseMvcActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected int setScreenOrientation() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void showLoading() {

    }

    @Override
    protected void hideLoading() {

    }
}
