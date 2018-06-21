package com.kang.mvp;

import android.app.Application;
import android.support.annotation.Nullable;

import com.kang.demolist.commom.BaseApplication;
import com.kang.mvp.dagger.BaseComponent;
import com.kang.mvp.dagger.BaseModule;
import com.kang.mvp.dagger.DaggerBaseComponent;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

public class MyApplication extends BaseApplication {

    private BaseComponent mBaseComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseComponent = DaggerBaseComponent.builder().baseModule(new BaseModule()).build();
    }

    public BaseComponent getBaseComponent() {
        return mBaseComponent;
    }
}
