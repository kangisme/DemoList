package com.kang.animation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kangren on 2018/3/21.
 */

public class ViewActivity extends AppCompatActivity
{

    @BindView(R.id.custom_3d)
    Custom3DView custom3d;

    @BindView(R.id.frame)
    ImageView frame;

    @BindView(R.id.list_view)
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.view_3d, R.id.view_frame, R.id.view_layout, R.id.view_activity})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.view_3d:
                listView.setVisibility(View.GONE);
                frame.setVisibility(View.GONE);
                custom3d.setVisibility(View.VISIBLE);
                break;
            case R.id.view_frame:
                custom3d.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
                frame.setVisibility(View.VISIBLE);
                frame.setBackgroundResource(R.drawable.frame_animation);
                AnimationDrawable drawable = (AnimationDrawable) frame.getBackground();
                drawable.start();
                break;
            case R.id.view_layout:
                custom3d.setVisibility(View.GONE);
                frame.setVisibility(View.GONE);
                String[] cities = {"上海", "北京", "苏州", "南京", "杭州", "武汉", "长沙"};
                listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities));
                listView.setVisibility(View.VISIBLE);
                break;
            case R.id.view_activity:
                Intent intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                // 必须在startActivity函数后执行
                overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
                break;
        }
    }
}
