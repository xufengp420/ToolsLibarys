package com.example.xufeng.toolslibary.okhttp.builder;


import com.example.xufeng.toolslibary.okhttp.OkHttpUtils;
import com.example.xufeng.toolslibary.okhttp.request.OtherRequest;
import com.example.xufeng.toolslibary.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
