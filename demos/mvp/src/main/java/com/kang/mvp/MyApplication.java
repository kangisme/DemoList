package com.kang.mvp;

import android.app.Application;
import android.support.annotation.Nullable;

import com.kang.demolist.commom.BaseApplication;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
