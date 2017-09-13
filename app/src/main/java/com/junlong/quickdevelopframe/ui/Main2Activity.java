package com.junlong.quickdevelopframe.ui;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.junlong.framecorelibrary.base.BaseMvcActivity;
import com.junlong.framecorelibrary.glide.GlideUtil;
import com.junlong.framecorelibrary.glide.RequestCallBack;
import com.junlong.framecorelibrary.rx.rxtools.OnEventCallBack;
import com.junlong.framecorelibrary.rx.rxtools.RxDoubleClick;
import com.junlong.quickdevelopframe.R;

import java.util.List;

public class Main2Activity extends BaseMvcActivity {


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
                toastShow("加载图片成功");
            }

            @Override
            public void requestFailed() {
                toastShow("加载图片失败");
            }
        });
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
}
