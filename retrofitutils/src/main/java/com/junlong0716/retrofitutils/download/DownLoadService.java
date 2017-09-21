package com.junlong0716.retrofitutils.download;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/9/19.
 */

public interface DownLoadService {

    @Streaming
    @GET
    Flowable<ResponseBody> startDownLoad(@Url String fileUrl);

}
