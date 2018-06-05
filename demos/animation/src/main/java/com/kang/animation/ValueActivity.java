package com.kang.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kangren on 2018/3/21.
 */

public class ValueActivity extends AppCompatActivity
{

    @BindView(R.id.value_animator)
    TextView valueAnimator;

    @BindView(R.id.object_animator)
    TextView objectAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.value_animator, R.id.object_animator})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.value_animator:
                ValueAnimator animatorXml = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.value_animator);
                animatorXml.setTarget(valueAnimator);
                animatorXml.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        valueAnimator.setAlpha((Float) animation.getAnimatedValue());
                    }
                });
                animatorXml.start();
                break;
            case R.id.object_animator:
                ObjectAnimator animator = ObjectAnimator.ofFloat(objectAnimator, "alpha", 1, 0);
                animator.setDuration(2000);
                animator.setRepeatCount(-1);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                // animator.addUpdateListener(new
                // ValueAnimator.AnimatorUpdateListener()
                // {
                // @Override
                // public void onAnimationUpdate(ValueAnimator animation)
                // {
                // float currentAlpha = (float) animation.getAnimatedValue();
                // valueAlpha.setAlpha(currentAlpha);
                // }
                // });
                animator.start();
                break;
        }
    }
}
