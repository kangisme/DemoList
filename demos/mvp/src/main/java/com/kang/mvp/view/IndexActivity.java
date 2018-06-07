package com.kang.mvp.view;

import java.util.ArrayList;

import android.os.Message;

import com.kang.demolist.commom.BaseIndexActivity;

/**
 * @author created by kangren on 2018/6/5 16:28
 */
public class IndexActivity extends BaseIndexActivity {
    @Override
    protected void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add("Dagger2.0");
    }

    @Override
    protected void initClasses() {
        mClasses = new ArrayList<>();
        mClasses.add(DaggerActivity.class);
        Message message = new Message();
    }
}
