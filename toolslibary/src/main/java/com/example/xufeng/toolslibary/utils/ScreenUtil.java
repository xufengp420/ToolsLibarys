package com.example.xufeng.toolslibary.utils;

import android.content.Context;
import android.util.DisplayMetrics;


/**
 * Created by Administrator on 2018/9/5 0005.
 */

public class ScreenUtil {

    private ScreenUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获得屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
