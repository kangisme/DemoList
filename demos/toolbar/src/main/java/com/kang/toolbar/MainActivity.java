package com.kang.toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.first)
    View first;

    @BindView(R.id.second)
    View second;

    @BindView(R.id.title)
    TitleBar title;

    // @BindView(R.id.tool_bar)
    // Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // toolBar.setTitle("Toolbar");
        // setSupportActionBar(toolBar);
        second.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                second.setVisibility(View.GONE);
            }
        }, 2000);
        first.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                first.setVisibility(View.VISIBLE);
            }
        }, 3000);
    }
}
