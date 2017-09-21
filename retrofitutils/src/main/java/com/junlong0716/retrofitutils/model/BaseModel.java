package com.junlong0716.retrofitutils.model;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/9/19.
 */
public interface BaseModel<T> {

    boolean isError();

    int getErrorCode();

    String getMsg();

    T getResult();

}
