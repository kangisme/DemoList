package com.kang.animation;


/**
 * @author created by kangren on 2018/6/5 16:01
 */
public class IndexActivity extends com.kang.demolist.commom.BaseIndexActivity {

    private Class<?>[] mClasses = {ViewActivity.class, ValueActivity.class};

    private String[] mStrings = {"ViewAnimation", "ValueAnimation"};

    @Override
    protected Class<?> getIndexClass(int position) {
        return mClasses[position];
    }

    @Override
    protected String[] getList() {
        return mStrings;
    }
}
