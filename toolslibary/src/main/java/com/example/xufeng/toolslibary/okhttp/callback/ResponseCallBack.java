package com.example.xufeng.toolslibary.okhttp.callback;

import android.util.Log;

import java.lang.reflect.ParameterizedType;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/12.
 */
public abstract class ResponseCallBack<T> extends Callback<T> {

    IResponseCallBack mIBack;

    public ResponseCallBack(IResponseCallBack mIBack) {
        this.mIBack = mIBack;
    }

    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);

    }

    @Override
    public void onAfter(int id) {
        super.onAfter(id);

    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {

        String string = response.body().string();
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (entityClass == String.class) {
            return (T) string;
        }
        T bean = mIBack.Transform(string, entityClass);

        return bean;


    }
}
