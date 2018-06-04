package com.kang.index;

/**
 * @author created by kangren on 2018/6/4 15:31
 */
public class IndexActivity extends com.kang.demolist.commom.IndexActivity {

    private Class<?>[] mClasses = {RotateDrawableActivity.class};

    private String[] mData = {"rotate drawable"};

    @Override
    protected Class<?> getIndexClass(int position) {
        return mClasses[position];
    }

    @Override
    protected String[] getList() {
        return mData;
    }
}
