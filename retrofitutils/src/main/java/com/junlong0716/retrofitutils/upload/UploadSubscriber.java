package com.junlong0716.retrofitutils.upload;

import android.content.Context;

import com.junlong0716.retrofitutils.exception.ServerException;
import com.junlong0716.retrofitutils.model.BaseModel;
import com.junlong0716.retrofitutils.utils.NetworkUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/9/19.
 */

public abstract class UploadSubscriber<T> implements Subscriber<Object> {

    protected Context mContext;

    public UploadSubscriber(Context context) {
        this.mContext = context;
    }

    protected Subscription mSubscription;

    @Override
    public void onSubscribe(Subscription s) {
        this.mSubscription = s;
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(Object o) {
        if (o instanceof Integer) {
            _onProgress((Integer) o);
        }

        if(o instanceof BaseModel) {
            BaseModel baseModel = (BaseModel) o;
            if(baseModel.isError()) {
                _onError(baseModel.getErrorCode(),baseModel.getMsg());
            }
            else {
                if(baseModel.getResult() != null) {
                    _onNext((T)baseModel.getResult());
                }
            }
        }
        mSubscription.request(1);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!NetworkUtils.isNetworkAvailable(mContext)) {
            _onError(ServerException.ERROR_NETWORK,"网络不可用");
        } else if (e instanceof ServerException) {
            _onError(((ServerException) e).getErrorCode(),e.getMessage());
        } else {
            _onError(ServerException.ERROR_OTHER,e.getMessage());
        }
    }

    protected abstract void _onNext(T result);

    protected abstract void _onProgress(Integer percent);

    protected abstract void _onError(int errorCode,String msg);


}
