package com.example.xufeng.toolslibary.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.Toast;

import com.example.xufeng.toolslibary.BaseAppManager;
import com.example.xufeng.toolslibary.R;
import com.example.xufeng.toolslibary.dialog.WaitProgressDialog;
import com.example.xufeng.toolslibary.utils.NetWorkUtil;
import com.example.xufeng.toolslibary.utils.StatusBarUtil;

/**
 * Created by xufeng on 2016/9/5.
 */

public abstract class BaseFrgmentActivity extends FragmentActivity {


    private WaitProgressDialog progressDialog;
    //计算dilaog 被show了几次
    private int showDilaogFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //竖屏设置
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // 设置沉浸样式
        setImmersionStyle();

        //APP 管理类
        BaseAppManager.getAppManager().addActivity(BaseFrgmentActivity.this);

        progressDialog = new WaitProgressDialog(this);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕宽度（像素）
        int height = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        //屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);//屏幕宽度(dp)
        int screenHeight = (int) (height / density);//屏幕高度(dp)
        //ldpi是120，     mdpi是160，    hdpi是240，     xhdpi是320       xxhdpi是480
        //ldpi 1dp=0.75px,mdpi 1dp=1px,  hdpi 1dp=1.5px  xhdpi 1dp=2px   xxhdpi 1dp=3px
        //ldpi 320*240,   mdpi 480*320,  hdpi 480*800,   xhdpi 1280*720  xxhdpi 1920*1080
//        HLog.e("TAG", "width:" + width + "px height:" + height + "px");
//        HLog.e("TAG", "width:" + screenWidth + "dp height:" + screenHeight + "dp");
//        HLog.e("TAG", "屏幕密度：" + density + "   dpi：" + densityDpi);

    }
    /**
     * ----------------------------------------------------------------------
     * --------------------------功能方法-------------------------------------
     * ----------------------------------------------------------------------
     */
    /**
     * 设置普通沉浸样式
     */
    protected void setImmersionStyle() {
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.statusbar_bg));
    }

    /**
     * Toast
     *
     * @param string
     */
    public void Toast(String string) {
        if (string.isEmpty()) {
            return;
        }
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示等待 dialog
     */
    public void showWaitDialog() {
        if (!NetWorkUtil.isNetWorkEnable(this)) {
            Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_LONG).show();
            return;
        }
        showDilaogFlag++;
        if (!progressDialog.isShowing()) {
            progressDialog.ShowWaitingDialog();
        }
    }

    /**
     * 消失等待 dialog
     */
    public void dismissWaitDialog() {
        try {
            showDilaogFlag--;
            if (showDilaogFlag == 0 && progressDialog.isShowing()) {
                progressDialog.WaitingDialogCancle();
            }
        } catch (Exception e) {

        }
    }

    long exitTime;

    /**
     * 退出
     */
    public void exitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            exitTime = System.currentTimeMillis();
            Toast("再按一次即可退出应用");
        } else {
            BaseAppManager.getAppManager().AppExit(this);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    /**
     * ----------------------------------------------------------------------
     * --------------------------抽象方法------------------------------------
     * ----------------------------------------------------------------------
     */

}
