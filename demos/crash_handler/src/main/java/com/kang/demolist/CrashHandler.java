package com.kang.demolist;

/**
 * Created by rex on 12/10/16.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        MyApplication.getApp().exit();
    }
}
