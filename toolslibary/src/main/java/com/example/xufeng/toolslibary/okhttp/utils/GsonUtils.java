package com.example.xufeng.toolslibary.okhttp.utils;

import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/10/9.
 */
public class GsonUtils {

    public static String toJsonStr(Object objcet) {
        Gson gson = new Gson();
        L.e(gson.toJson(objcet)+"----Request----");
        return gson.toJson(objcet);
    }

    public static String toBase64Str(String string) {

        return Base64.encodeToString(string.getBytes(), Base64.NO_WRAP);
    }
}
