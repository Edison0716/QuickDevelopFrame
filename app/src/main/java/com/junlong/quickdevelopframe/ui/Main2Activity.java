package com.junlong.quickdevelopframe.ui;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.junlong.framecorelibrary.base.BaseMvcActivity;
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
                Toast.makeText(getApplicationContext(),"刷新",Toast.LENGTH_SHORT).show();

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
