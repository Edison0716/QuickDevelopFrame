package com.junlong.quickdevelopframe.ui;

import android.Manifest;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.junlong.framecorelibrary.base.BaseMvcActivity;
import com.junlong.framecorelibrary.easypermissions.AfterPermissionGranted;
import com.junlong.framecorelibrary.easypermissions.AppSettingsDialog;
import com.junlong.framecorelibrary.easypermissions.EasyPermissions;
import com.junlong.framecorelibrary.glide.GlideUtil;
import com.junlong.framecorelibrary.glide.RequestCallBack;
import com.junlong.framecorelibrary.rx.rxtools.OnEventCallBack;
import com.junlong.framecorelibrary.rx.rxtools.RxDoubleClick;
import com.junlong.quickdevelopframe.R;

import java.util.List;

public class Main2Activity extends BaseMvcActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {

    private static final String TAG = "MainActivity";
    private static final String[] LOCATION_AND_CONTACTS =
            {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CONTACTS};

    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_LOCATION_CONTACTS_PERM = 124;

    private List<String> mValueList;

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
        //GlideUtil.loadBlurImage(this, R.mipmap.ic_launcher, ivImage, 18);
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
                cameraTask();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private boolean hasCameraPermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA);
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask() {
        if (hasCameraPermission()) {
            // Have permission, do the thing!
            Toast.makeText(this, "TODO: Camera things", Toast.LENGTH_LONG).show();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.rationale_camera),
                    RC_CAMERA_PERM,
                    Manifest.permission.CAMERA);
        }

    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        showSuccessToast(getString(R.string.grant_success));
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }
}
