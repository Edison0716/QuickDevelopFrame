package com.junlong.framecorelibrary.mvp.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.junlong.framecorelibrary.BaseApplication;
import com.junlong.framecorelibrary.rx.rxbus.RxBus;
import com.junlong.framecorelibrary.util.ScreenUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/7/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Activity mActivity;
    private String mShortClassName;
    private final int SCREEN_DEFAULT = 0;//跟随系统
    private final int SCREEN_PORTRAIT = 1;//设置竖屏
    private final int SCREEN_LANDSCOPE = 2;//设置横屏
    private int screenOrientationFlag = 0; //默认是跟随系统
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetContentView();
        setContentView(getLayoutId());
        mActivity = this;
        getActivityInfo();
        initData();
        initView();
    }

    private void getActivityInfo() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        //类名
        mShortClassName = info.topActivity.getShortClassName();
        Log.d(mShortClassName, "已创建");
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

    protected abstract int setScreenOrientation();//设置横竖屏

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

    //绑定视图id
    public <T extends View> T bindView(@IdRes int id) {
        View viewById = findViewById(id);
        return (T) viewById;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(mShortClassName, "已销毁");

        //rxBus取消订阅
        if (RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().unregister(this);
        }

        //leakcanady 内存泄漏监控
        BaseApplication.getRefWatcher(this).watch(this);

        //取消rxjava 订阅
        dispose();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(mShortClassName, "已重启");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(mShortClassName, "已停止");
    }

    /**
     * @param clazz
     * @param bundle 跳转页面
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
     * @param clazz  目标Activity
     * @param bundle 数据
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        readyGo(clazz, bundle);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    //添加rxjava队列
    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    //接触rxjava订阅
    public void dispose() {
        if (compositeDisposable != null) compositeDisposable.dispose();
    }

}
