package com.junlong.quickdevelopframe.ui;

import com.junlong.quickdevelopframe.model.ItemModel;
import com.junlong.quickdevelopframe.ui.rx.RxClickActivity;

import java.util.List;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/7/14.
 */

public class RxFragment extends MainFragment {
    @Override
    public void onItemClick(int position) {
        if (position==0) readyGo(RxClickActivity.class);
        if (position== 1)readyGo(Main2Activity.class);
    }

    @Override
    public void fillData(List<ItemModel> items) {
        items.add(new ItemModel("RxClick 防抖动",//
                "1.基于RxBinding封装的RxClick，防止多次点击按钮。"));
        items.add(new ItemModel("RxSearch 查询停留",//
                "2.基于RxBinding封装的RxSearch，查询停留。"));
    }
}
