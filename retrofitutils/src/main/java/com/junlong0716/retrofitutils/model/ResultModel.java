package com.junlong0716.retrofitutils.model;

/**
 * Created by ${巴黎没有摩天轮Li} on 2017/9/19.
 */

public class ResultModel<T> implements BaseModel<T> {
    private int code;

    private T data;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean isError() {
        return code != 200;
    }

    @Override
    public int getErrorCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return message;
    }

    @Override
    public T getResult() {
        return data;
    }
}
