package com.junlong.quickdevelopframe.ui.rx;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.junlong.framecorelibrary.BaseApplication;
import com.junlong.framecorelibrary.base.BaseMvcActivity;
import com.junlong.framecorelibrary.rx.rxbase.OnClickCallBack;
import com.junlong.framecorelibrary.rx.rxbase.RxClick;
import com.junlong.framecorelibrary.util.CacheUtils;
import com.junlong.quickdevelopframe.R;

public class RxClickActivity extends BaseMvcActivity {
    private int i = 0;

    @Override
    protected int setScreenOrientation() {
        return 1;
    }

    @Override
    protected void initView() {
        Button btJoggle = bindView(R.id.bt_joggle);
        final TextView tvCount = bindView(R.id.tv_count);
        long size = CacheUtils.getInstance().getCacheSize();
        tvCount.setText(String.valueOf(size));
        RxClick.rxOnClick(btJoggle, 2, new OnClickCallBack() {
            @Override
            public void OnClick() {
                i++;
                tvCount.setText(String.valueOf(i));
                Log.d("count",String.valueOf(i));
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
        return R.layout.activity_rx_click;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //leakcanady 内存泄漏监控
        BaseApplication.getRefWatcher(this).watch(this);
    }
}
