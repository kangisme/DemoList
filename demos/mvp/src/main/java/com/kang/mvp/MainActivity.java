package com.kang.mvp;

import javax.inject.Inject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
{
    @Inject
    Cloth cloth;

    @Inject
    Shoe shoe;

    @Inject
    Clothes clothes;

    @BindView(R.id.content)
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainComponent build = DaggerMainComponent.builder().mainModule(new MainModule()).build();
        build.inject(this);
        content.postDelayed(new Runnable() {
            @Override
            public void run() {
                content.setText(cloth.toString() + shoe.toString() + clothes.toString());
            }
        }, 2000);
    }
}
