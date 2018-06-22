package com.kang.index;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author created by kangren on 2018/6/22 18:29
 */
public class StickActivity extends Activity {
    StickyHeightLayout stickyNavLayout;

    RecyclerView mRecyclerView;

    MainAdapter mMainAdapter;

    LinearLayout mTopView;

    LinearLayout mTabView;

    ArrayList<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatas = new ArrayList<>();
        stickyNavLayout = (StickyHeightLayout) findViewById(R.id.stickyNavLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_bottomview);
        changeItems(20);
        mMainAdapter = new MainAdapter(this, mDatas);
        mRecyclerView.setAdapter(mMainAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTopView = (LinearLayout) findViewById(R.id.id_topview);
        mTopView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(StickActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });
        mTabView = (LinearLayout) findViewById(R.id.id_indicatorview);
        mTabView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(StickActivity.this, "click2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeItems(int num)
    {
        stickyNavLayout.setContentHeight(num);
        mDatas.clear();
        for (int i = 0; i < num; i++)
        {
            mDatas.add("fasdfasdf");
        }
    }
}
