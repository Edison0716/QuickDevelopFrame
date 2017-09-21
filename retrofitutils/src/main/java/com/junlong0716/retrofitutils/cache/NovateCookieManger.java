package com.junlong0716.retrofitutils.cache;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/9/19.
 */

public class NovateCookieManger implements CookieJar {

    private static final String TAG = "NovateCookieManger";
    private static Context mContext;
    private static PersistentCookieStore cookieStore;

    /**
     * Mandatory constructor for the NovateCookieManger
     */
    public NovateCookieManger(Context context) {
        mContext = context;
        if (cookieStore == null) {
            cookieStore = new PersistentCookieStore(mContext);
        }
    }

    /**
     * 方法会在服务端给客户端发送Cookie时调用。此时需要我们自己实现保存Cookie的方式。
     * 这里使用了最简单的Map来保存域名与Cookie的关系
     * @param url
     * @param cookies
     */
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    /**
     * 每当这个client访问到某一个域名时，就会通过此方法获取保存的Cookie，并且发送给服务器
     * @param url
     * @return
     */
    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }

}
