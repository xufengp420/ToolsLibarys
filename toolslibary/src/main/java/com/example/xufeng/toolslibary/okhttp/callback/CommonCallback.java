package com.example.xufeng.toolslibary.okhttp.callback;

import com.example.xufeng.toolslibary.mvp.BaseMvpView;
import com.example.xufeng.toolslibary.okhttp.utils.L;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/12.
 */
public abstract class CommonCallback<T> extends Callback<T> {

    Gson mGson = new Gson();

    BaseMvpView view;

    public CommonCallback(BaseMvpView view) {
        super();
        this.view = view;
    }

    public BaseMvpView getMvpView() {
        return view;
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {

        String string = response.body().string();
        L.e(string);
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (entityClass == String.class) {
            return (T) string;
        }
        T bean = mGson.fromJson(string, entityClass);
        return bean;

    }
}
