package com.example.xufeng.toolslibary.okhttp.callback;

/**
 * Created by Administrator on 2016/9/12.
 */
public interface IResponseCallBack {
    <T> T Transform(String response, Class<T> classOfT);
}
