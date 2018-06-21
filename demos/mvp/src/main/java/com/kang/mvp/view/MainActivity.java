package com.kang.mvp.view;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.kang.mvp.MyApplication;
import com.kang.mvp.R;
import com.kang.mvp.dagger.DaggerMainComponent;
import com.kang.mvp.dagger.MainComponent;
import com.kang.mvp.dagger.MainModule;
import com.kang.mvp.model.Cloth;
import com.kang.mvp.model.ClothHandler;
import com.kang.mvp.model.Clothes;
import com.kang.mvp.model.Shoe;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Lazy;

public class MainActivity extends AppCompatActivity
{
    @Inject
    @Named("red")
    Cloth redCloth;

    @Inject
    @Named("blue")
    Cloth blueCloth;

    @Inject
    Clothes clothes;

    @Inject
    ClothHandler mClothHandler;

    @BindView(R.id.content)
    TextView content;

    @BindView(R.id.button)
    Button mButton;

    @Inject
    @Named("white")
    Lazy<Cloth> whiteCloth;

    @Inject
    Provider<Shoe> shoe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MyApplication application = (MyApplication) getApplication();
        application.
                getBaseComponent().
                getSubMainComponent(new MainModule())
                .inject(this);
//        DaggerMainComponent.builder()
//                .baseComponent(((MyApplication)getApplication())
//                .getBaseComponent())
//                .mainModule(new MainModule())
//                .build()
//                .inject(this);
        content.setText("红布料加工后变成了" + mClothHandler.handle(redCloth) + "\nclothHandler地址:" + mClothHandler);
        Logger.d("inject done ...");
        Logger.d("1 use redCloth instance ..");
        Logger.d("redCloth:" + whiteCloth.get());
        Logger.d("2 use redCloth instance ..");
        Logger.d("redCloth:" + whiteCloth.get());
        Logger.d("1 use shoe instance ..");
        Logger.d("shoe:" + shoe.get());
        Logger.d("2 use shoe instance ..");
        Logger.d("shoe:" + shoe.get());
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }
}
