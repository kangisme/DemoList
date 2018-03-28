package com.kang.demolist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by rex on 12/9/16.
 */

public class MyApplication extends Application {

    private static MyApplication context;
    private List<Activity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        init();
    }

    private void init() {
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
        activityList = Collections.synchronizedList(new ArrayList<Activity>());
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                activityList.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                activityList.remove(activity);
            }
        });
    }


    public static MyApplication getApp() {
        return context;
    }

    public void exit() {
        Iterator<Activity> iterator = activityList.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            activity.finish();
            iterator.remove();
        }
        activityList.clear();
        System.exit(0); // this is line cannot be commented out;
    }

    public void showActivityList() {
        StringBuilder builder = new StringBuilder();
        builder.append("list size is ").append(activityList.size()).append("\n");
        for (Activity activity : activityList) {
            builder.append(activity.getLocalClassName()).append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show();
    }
}
