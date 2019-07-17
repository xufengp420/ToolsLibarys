package com.example.xufeng.toolslibary.activity;

import android.os.Bundle;

import com.example.xufeng.toolslibary.mvp.BaseMvpView;
import com.example.xufeng.toolslibary.mvp.MvpPresenter;

/**
 * Created by Administrator on 2018/7/7 0007.
 */

public abstract class BaseMvpFrgmentActivity<P extends MvpPresenter> extends BaseFrgmentActivity implements BaseMvpView {
    protected P mPresenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
//        if (mPresenter == null) {
//            throw new NullPointerException("Presenter is null! Do you return null in createPresenter()?");
//        }
//        mPresenter.onMvpAttachView(this, savedInstanceState);
    }

    @Override
    public void showLoding() {
        showWaitDialog();
    }

    @Override
    public void hideLoading() {
        dismissWaitDialog();
    }

    @Override
    public void ToastMsg(String msg) {
        Toast(msg);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onMvpStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onMvpResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onMvpPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onMvpStop();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenter != null) {
            mPresenter.onMvpSaveInstanceState(outState);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onMvpDetachView(false);
            mPresenter.onMvpDestroy();
        }
    }
}
