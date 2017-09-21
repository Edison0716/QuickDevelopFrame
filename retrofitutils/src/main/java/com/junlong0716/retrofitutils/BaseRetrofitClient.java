package com.junlong0716.retrofitutils;

import android.content.Context;

import com.junlong0716.retrofitutils.cache.CaheInterceptor;
import com.junlong0716.retrofitutils.cache.NovateCookieManger;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/9/19.
 */

public class BaseRetrofitClient {
    private static BaseRetrofitClient instance = null;
    private String mBaseUrl;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;

    public BaseRetrofitClient() {
    }

    public synchronized static BaseRetrofitClient getInstance() {
        if (instance == null) {
            instance = new BaseRetrofitClient();
        }
        return instance;
    }

    //set base url
    public BaseRetrofitClient setBaseUrl(String baseUrl) {
        this.mBaseUrl = baseUrl;
        return this;
    }

    //init RetrofitClient
    public void init(Context context) {

        if (mBaseUrl == null) throw new IllegalArgumentException("Base URL required.");

        // .addInterceptor(new LogInterceptor())
        mOkHttpClient = new OkHttpClient.Builder()
                // .addInterceptor(new LogInterceptor())
                .cookieJar(new NovateCookieManger(context))
                .cache(new Cache(new File(context.getExternalCacheDir(), "net_request_cache"), 10 * 1024 * 1024))
                .addInterceptor(new CaheInterceptor(context))
                .addNetworkInterceptor(new CaheInterceptor(context))
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        this.mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持RxJava
                .build();
        RetrofitUtils.setRetrofitClient(this);
    }

    //get the client
    public Retrofit getRetrofitClient() {
        return mRetrofit;
    }

    public OkHttpClient getOkHttpClient(){
        return mOkHttpClient;
    }
}
