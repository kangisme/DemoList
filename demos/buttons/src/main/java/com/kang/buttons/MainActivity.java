package com.kang.buttons;

import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{

    @BindView(R.id.register)
    RegisterButton register;

    @BindView(R.id.lottie_image)
    LottieAnimationView image;

    @BindView(R.id.shade_view)
    ImageView shadeView;

    @BindView(R.id.clipping_text)
    TextView clippingText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                image.playAnimation();
            }
        });

        //获取outLine，我们需要使用ViewoutLineProvider
        ViewOutlineProvider viewOutlineProvider=new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //修改outLine的形状，这里是设置分别设置左上右下，以及Radius
                outline.setRoundRect(0,0,view.getWidth(),view.getHeight(),50);
            }
        };
        //将需要控件重写设置形状
        clippingText.setOutlineProvider(viewOutlineProvider);
    }

    @OnClick(R.id.register)
    public void onViewClicked()
    {
        register.startAnimation();
    }
}
