package com.kang.mvp.view;

import javax.inject.Inject;
import javax.inject.Named;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kang.mvp.dagger.DaggerMainComponent;
import com.kang.mvp.dagger.MainComponent;
import com.kang.mvp.dagger.MainModule;
import com.kang.mvp.R;
import com.kang.mvp.model.Cloth;
import com.kang.mvp.model.Clothes;
import com.kang.mvp.model.Shoe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaggerActivity extends AppCompatActivity
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
                content.setText("blueCloth是否等于Clothes中色Cloth？" + (blueCloth == clothes.getCloth()));
            }
        }, 2000);
    }
}
