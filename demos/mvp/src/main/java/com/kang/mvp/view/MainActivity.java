package com.kang.mvp.view;

import javax.inject.Inject;
import javax.inject.Named;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.kang.mvp.R;
import com.kang.mvp.dagger.DaggerMainComponent;
import com.kang.mvp.dagger.MainComponent;
import com.kang.mvp.dagger.MainModule;
import com.kang.mvp.model.Cloth;
import com.kang.mvp.model.ClothHandler;
import com.kang.mvp.model.Clothes;
import com.kang.mvp.model.Shoe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{
    @Inject
    @Named("red")
    Cloth redCloth;

    @Inject
    @Named("blue")
    Cloth blueCloth;

    @Inject
    Shoe shoe;

    @Inject
    Clothes clothes;

    @Inject
    ClothHandler mClothHandler;

    @BindView(R.id.content)
    TextView content;

    @BindView(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainComponent build = DaggerMainComponent.builder().mainModule(new MainModule()).build();
        build.inject(this);
        content.setText("红布料加工后变成了" + mClothHandler.handle(redCloth) + "\nclothHandler地址:" + mClothHandler);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }
}
