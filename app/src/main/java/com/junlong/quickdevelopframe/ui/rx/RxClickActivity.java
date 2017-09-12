package com.junlong.quickdevelopframe.ui.rx;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.junlong.framecorelibrary.base.BaseMvcActivity;
import com.junlong.framecorelibrary.rx.rxtools.OnClickCallBack;
import com.junlong.framecorelibrary.rx.rxtools.RxClick;
import com.junlong.framecorelibrary.util.CacheUtils;
import com.junlong.framecorelibrary.util.StatusBarUtils;
import com.junlong.quickdevelopframe.R;

import io.reactivex.disposables.Disposable;

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

            @Override
            public void getDisposable(Disposable d) {
                addDisposable(d);
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initStatusBar() {
        StatusBarUtils.setColorForSwipeBack(this,getResources().getColor(R.color.colorPrimary),0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_click;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void showLoading() {

    }

    @Override
    protected void hideLoading() {

    }
}
