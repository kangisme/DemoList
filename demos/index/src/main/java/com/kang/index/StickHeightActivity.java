package com.kang.index;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StickHeightActivity extends AppCompatActivity
{

    StickyHeightLayout stickyNavLayout;

    RecyclerView id_bottomview;

    MainAdapter adapter;

    LinearLayout id_topview;

    LinearLayout id_indicatorview;

    ArrayList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        strings = new ArrayList<>();
        stickyNavLayout = (StickyHeightLayout) findViewById(R.id.stickyNavLayout);
        id_bottomview = (RecyclerView) findViewById(R.id.id_bottomview);
        changeItems(20);
        adapter = new MainAdapter(this, strings);
        id_bottomview.setAdapter(adapter);
        id_bottomview.setLayoutManager(new LinearLayoutManager(this));
        id_topview = (LinearLayout) findViewById(R.id.id_topview);
        id_topview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(StickHeightActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });
        id_indicatorview = (LinearLayout) findViewById(R.id.id_indicatorview);
        id_indicatorview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(StickHeightActivity.this, "click2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeItems(int num)
    {
        stickyNavLayout.setContentHeight(num);
        strings.clear();
        for (int i = 0; i < num; i++)
        {
            strings.add("fasdfasdf");
        }
    }
}
