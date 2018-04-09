package com.kang.shake_button;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{

    @BindView(R.id.register)
    RegisterButton register;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.register)
    public void onViewClicked() {
        register.startAnimation();
    }
}
