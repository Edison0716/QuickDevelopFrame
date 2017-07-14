package com.junlong.framecorelibrary.mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/7/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mActivity = this;
        initStatusBar();
        initData();
        initView();
    }

    protected abstract void initView();//初始化 view

    protected abstract void initData();//初始化 数据

    protected abstract void initStatusBar();//初始化状态栏

    protected abstract int getLayoutId();

    public void toastShow(int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public <T extends View> T bindView(@IdRes int id){
        View viewById = findViewById(id);
        return (T) viewById;
    }
}
