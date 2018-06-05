package com.kang.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Activity切换动画测试类
 * Created by kangren on 2018/3/22.
 */

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("This is TestActivity");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        setContentView(textView);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
    }
}
