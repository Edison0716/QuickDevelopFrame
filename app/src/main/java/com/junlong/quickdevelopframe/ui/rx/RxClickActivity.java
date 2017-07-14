package com.junlong.quickdevelopframe.ui.rx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.junlong.framecorelibrary.rx.rxbase.OnClickCallBack;
import com.junlong.framecorelibrary.rx.rxbase.RxClick;
import com.junlong.quickdevelopframe.MyApp;
import com.junlong.quickdevelopframe.R;
import com.squareup.leakcanary.RefWatcher;

public class RxClickActivity extends AppCompatActivity {
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_click);
        Button btJoggle = (Button) findViewById(R.id.bt_joggle);
        final TextView tvCount = (TextView) findViewById(R.id.tv_count);
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
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApp.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
