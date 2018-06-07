package com.kang.index;

import java.util.ArrayList;

import com.kang.demolist.commom.BaseIndexActivity;

/**
 * @author created by kangren on 2018/6/4 15:31
 */
public class IndexActivity extends BaseIndexActivity {

    @Override
    protected void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add("RotateDrawableActivity");
        mTitles.add("BitmapActivity");
    }

    @Override
    protected void initClasses() {
        mClasses = new ArrayList<>();
        mClasses.add(RotateDrawableActivity.class);
        mClasses.add(BitmapActivity.class);
    }
}
