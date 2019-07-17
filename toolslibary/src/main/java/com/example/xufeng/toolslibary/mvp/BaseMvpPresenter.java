package com.example.xufeng.toolslibary.mvp;

import android.os.Bundle;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/7/7 0007.
 */

public class BaseMvpPresenter<V extends BaseMvpView> implements MvpPresenter<V> {

    private WeakReference<V> viewRef;

    protected V getView() {
        return viewRef.get();
    }

    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    private void attach(V view, Bundle savedInstanceState) {
        viewRef = new WeakReference<V>(view);
    }

    private void etach(boolean retainInstance) {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    @Override
    public void onMvpAttachView(V view, Bundle savedInstanceState) {
        attach(view, savedInstanceState);
    }

    @Override
    public void onMvpStart() {

    }

    @Override
    public void onMvpResume() {

    }

    @Override
    public void onMvpPause() {

    }

    @Override
    public void onMvpStop() {

    }

    @Override
    public void onMvpSaveInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onMvpDetachView(boolean retainInstance) {

    }

    @Override
    public void onMvpDestroy() {

    }
}
