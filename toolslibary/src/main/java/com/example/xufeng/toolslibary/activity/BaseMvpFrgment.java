package com.example.xufeng.toolslibary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xufeng.toolslibary.mvp.BaseMvpView;
import com.example.xufeng.toolslibary.mvp.MvpPresenter;

/**
 * Created by Administrator on 2018/7/7 0007.
 */

public abstract class BaseMvpFrgment<P extends MvpPresenter> extends BaseFrgment implements BaseMvpView {
    protected P mPresenter;

    protected abstract P createPresenter();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter == null) {
//            throw new NullPointerException("Presenter is null! Do you return null in createPresenter()?");
        }
        mPresenter.onMvpAttachView(this, savedInstanceState);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
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
    public void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onMvpStart();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onMvpResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onMvpPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onMvpStop();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenter != null) {
            mPresenter.onMvpSaveInstanceState(outState);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onMvpDetachView(false);
            mPresenter.onMvpDestroy();
        }
    }
}
