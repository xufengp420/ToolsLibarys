package com.example.xufeng.toolslibary.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public abstract class BaseFrgment extends Fragment {


    private WaitProgressDialog progressDialog;
    //计算dilaog 被show了几次
    private int showDilaogFlag = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new WaitProgressDialog(getActivity());
    }

    /**
     * ----------------------------------------------------------------------
     * --------------------------功能方法-------------------------------------
     * ----------------------------------------------------------------------
     */

    /**
     * Toast
     *
     * @param string
     */
    public void Toast(String string) {
        if (string.isEmpty()) {
            return;
        }
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示等待 dialog
     */
    public void showWaitDialog() {
        if (!NetWorkUtil.isNetWorkEnable(getActivity())) {
            Toast.makeText(getActivity(), getString(R.string.no_internet), Toast.LENGTH_LONG).show();
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

    /**
     * ----------------------------------------------------------------------
     * --------------------------抽象方法------------------------------------
     * ----------------------------------------------------------------------
     */

}
