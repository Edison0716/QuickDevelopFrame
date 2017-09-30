package com.junlong.quickdevelopframe.ui;

import android.Manifest;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.junlong.framecorelibrary.base.BaseMvcActivity;
import com.junlong.framecorelibrary.glide.GlideUtil;
import com.junlong.framecorelibrary.glide.RequestCallBack;
import com.junlong.framecorelibrary.permissions.Permission;
import com.junlong.framecorelibrary.permissions.RxPermissions;
import com.junlong.framecorelibrary.rx.rxtools.OnEventCallBack;
import com.junlong.framecorelibrary.rx.rxtools.RxDoubleClick;
import com.junlong.quickdevelopframe.R;

import io.reactivex.functions.Consumer;

public class Main2Activity extends BaseMvcActivity implements View.OnClickListener {


    private RxPermissions mPermissions;
    private static final String TAG = "USER_PERMISSIONS";
    @Override
    protected int setScreenOrientation() {
        return 1;
    }

    @Override
    protected void initView() {
        Button etSearch = bindView(R.id.et_search);
        RxDoubleClick.onDoubleClick(etSearch, new OnEventCallBack() {
            @Override
            public void doEvent() {
                Toast.makeText(getApplicationContext(), "刷新", Toast.LENGTH_SHORT).show();

            }
        });
        ImageView ivImage = bindView(R.id.iv_image);
        GlideUtil.loadImage(this, R.mipmap.avatar, ivImage, new RequestCallBack() {
            @Override
            public void requestSuccess() {
                showSuccessToast("图片加载成功！");
            }

            @Override
            public void requestFailed() {
                showErrorToast("图片加载失败！");
            }
        });

        bindView(R.id.bt_permissions).setOnClickListener(this);
        mPermissions = new RxPermissions(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void showLoading() {

    }

    @Override
    protected void hideLoading() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_permissions:
                mPermissions.requestEach(Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO)
                                    .subscribe(new Consumer<Permission>(){
                                        @Override
                                        public void accept(Permission permission) throws Exception {
                                            if (permission.granted) {
                                                // 用户已经同意该权限
                                                Log.d(TAG, permission.name + " is granted.");
                                            } else if (permission.shouldShowRequestPermissionRationale) {
                                                // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                                                Log.d(TAG, permission.name + " is denied. More info should be provided.");
                                            } else {
                                                // 用户拒绝了该权限，并且选中『不再询问』
                                                Log.d(TAG, permission.name + " is denied.");
                                            }
                                        }
                                    });
                break;
        }
    }

}
