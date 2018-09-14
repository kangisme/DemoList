package com.kang.animation;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author created by Administrator on 2018/9/14 0014 10:12
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
