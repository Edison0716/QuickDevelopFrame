package com.junlong.quickdevelopframe.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.junlong.framecorelibrary.base.BaseFragment;
import com.junlong.quickdevelopframe.model.ItemModel;
import com.junlong.quickdevelopframe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/7/14.
 */

public abstract class MainFragment extends BaseFragment{
    private Context mContext;
    private RecyclerView mRvList;
    private List<ItemModel> mItems;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    protected void initData() {
        mItems = new ArrayList<>();
        fillData(mItems);
    }

    @Override
    protected void initView() {

        mRvList = bindView(R.id.recyclerView);
        MainAdapter adapter = new MainAdapter(R.layout.item_main_list, mItems);

        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRvList.setLayoutManager(new LinearLayoutManager(mContext));
        mRvList.setItemAnimator(new DefaultItemAnimator());
        mRvList.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));

        mRvList.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    private class MainAdapter extends BaseQuickAdapter<ItemModel,BaseViewHolder>{

        public MainAdapter(@LayoutRes int layoutResId, @Nullable List<ItemModel> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, ItemModel item) {
            helper.setText(R.id.title, item.title);
            helper.setText(R.id.des, item.des);
            helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(helper.getAdapterPosition());
                }
            });
        }
    }

    public abstract void onItemClick(int position);

    public abstract void fillData(List<ItemModel> items);


}
