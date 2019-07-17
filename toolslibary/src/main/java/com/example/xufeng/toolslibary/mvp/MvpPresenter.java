package com.example.xufeng.toolslibary.mvp;

import android.os.Bundle;

/**
 * Created by Administrator on 2018/7/7 0007.
 */

public interface MvpPresenter<V extends BaseMvpView> {

    void onMvpAttachView(V view, Bundle savedInstanceState);

    void onMvpStart();

    void onMvpResume();

    void onMvpPause();

    void onMvpStop();

    void onMvpSaveInstanceState(Bundle savedInstanceState);

    void onMvpDetachView(boolean retainInstance);

    void onMvpDestroy();
}
