package com.kang.mvp.view;

import javax.inject.Inject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kang.mvp.MyApplication;
import com.kang.mvp.R;
import com.kang.mvp.dagger.DaggerSecondComponent;
import com.kang.mvp.dagger.SecondModule;
import com.kang.mvp.model.Cloth;
import com.kang.mvp.model.ClothHandler;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author created by kangren on 2018/6/14 18:13
 */
public class SecondActivity extends AppCompatActivity
{
    @Inject
    Cloth blueCloth;

    @Inject
    ClothHandler mClothHandler;

    @BindView(R.id.content)
    TextView mContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        DaggerSecondComponent.builder().baseComponent(((MyApplication)getApplication()).getBaseComponent()).
                secondModule(new SecondModule()).build().inject(this);
        mContent.setText("蓝布料加工后变成了" + mClothHandler.handle(blueCloth) + "\nclothHandler地址:" + mClothHandler);
    }
}
