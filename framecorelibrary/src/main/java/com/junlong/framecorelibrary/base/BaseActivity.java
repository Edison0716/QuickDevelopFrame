package com.junlong.framecorelibrary.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.junlong.framecorelibrary.BaseApplication;
import com.junlong.framecorelibrary.rx.rxbus.RxBus;
import com.junlong.framecorelibrary.swipebackhelper.SwipeBackHelper;
import com.junlong.framecorelibrary.swipebackhelper.SwipeListener;
import com.junlong.framecorelibrary.util.ScreenUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/7/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Activity mActivity;
    private String mShortClassName;
    private final int SCREEN_DEFAULT = 0;//following system orientation
    private final int SCREEN_PORTRAIT = 1;//portrait screen
    private final int SCREEN_LANDSCOPE = 2;//landscape screen
    private int screenOrientationFlag = 0; //following system orientation
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetContentView();
        setContentView(getLayoutId());
        SwipeBackHelper.onCreate(this);//init swipeback
        mActivity = this;
        getActivityInfo();
        initData();
        initView();
    }

    private void getActivityInfo() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        //class name
        mShortClassName = info.topActivity.getShortClassName();
        Log.d(mShortClassName, "onCreate");
    }

    private void doBeforeSetContentView() {
        initStatusBar();
        screenOrientationFlag = setScreenOrientation();
        switch (screenOrientationFlag) {
            case SCREEN_DEFAULT:
                break;
            case SCREEN_PORTRAIT:
                ScreenUtils.setPortrait(this);
                break;
            case SCREEN_LANDSCOPE:
                ScreenUtils.setLandscape(this);
                break;
        }
    }

    protected abstract int setScreenOrientation();//set screen orientation

    protected abstract void initView();//init view

    protected abstract void initData();//init data

    protected abstract void initStatusBar();//init status bar

    protected abstract int getLayoutId();

    public void toastShow(int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    //bind view
    public <T extends View> T bindView(@IdRes int id) {
        View viewById = findViewById(id);
        return (T) viewById;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(mShortClassName, "onDestroy");

        //rxBus cancel registered
        if (RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().unregister(this);
        }

        //cancel rxjava registered
        dispose();

        //destory swipeback
        SwipeBackHelper.onDestroy(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(mShortClassName, "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(mShortClassName, "onStop");
    }

    /**
     * @param clazz
     * @param bundle jump activity
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void readyGo(Class<?> clazz) {
        readyGo(clazz, null);
    }

    /**
     * @param clazz  aim activity
     * @param bundle data
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        readyGo(clazz, bundle);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz       aim activity
     * @param requestCode send flag code
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz       aim activity
     * @param requestCode send flag code
     * @param bundle      data
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    //add rxjava queue
    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    //releace rxjava register
    private void dispose() {
        if (compositeDisposable != null) compositeDisposable.dispose();
    }


    public void initSwipeBack() {
        SwipeBackHelper.getCurrentPage(this)//获取当前页面
                .setSwipeBackEnable(true)//设置是否可滑动
                .setSwipeEdge(200)//可滑动的范围。px。200表示为左边200px的屏幕
                .setSwipeEdgePercent(0.2f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕
                .setSwipeSensitivity(0.5f)//对横向滑动手势的敏感程度。0为迟钝 1为敏感
                .setScrimColor(Color.GRAY)//底层阴影颜色
                .setClosePercent(0.8f)//触发关闭Activity百分比
                .setSwipeRelateEnable(false)//是否与下一级activity联动(微信效果)。默认关
                .setSwipeRelateOffset(500)//activity联动时的偏移量。默认500px。
                .setDisallowInterceptTouchEvent(true)//不抢占事件，默认关（事件将先由子View处理再由滑动关闭处理）
                .addListener(new SwipeListener() {//滑动监听

                    @Override
                    public void onScroll(float percent, int px) {//滑动的百分比与距离
                    }

                    @Override
                    public void onEdgeTouch() {//当开始滑动
                    }

                    @Override
                    public void onScrollToClose() {//当滑动关闭
                    }
                });
    }
}
