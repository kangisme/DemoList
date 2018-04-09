package com.kang.shake_button;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

/**
 * <一句话功能简述> <签到动画按钮>
 *
 * @author jixiongxu
 * @version [版本号, 2018/3/13]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

public class RegisterButton extends FrameLayout implements Animation.AnimationListener
{
    private Context mContext;

    // 默认插值器
    private LinearInterpolator interpolator;

    // 文本
    private View textView;

    // 背景
    private View imageView;

    // 文本动画，抖动
    private Animation textAnimation;

    // 背景动画，缩放和透明度变化
    private Animation backgroundAnimation;

    // 动画是否正在运行
    private boolean isRun = false;

    public RegisterButton(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init()
    {
        View mRootView = inflate(mContext, R.layout.usercenter_register_button, this);
        textView = mRootView.findViewById(R.id.text);
        imageView = mRootView.findViewById(R.id.image);
        imageView.setVisibility(GONE);
    }

    /**
     * 判断当天是否显示过动画
     *
     * @return res
     */
//    private boolean needPlayerAnimation()
//    {
//        String SHOWED = "data_last_show_register_animation";
//
//        SharedPreferences.Editor editor = PreferencesUtils.getEditor(mContext);
//        SharedPreferences prefs = PreferencesUtils.getPreferences(mContext);
//        int lastShow = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
//
//        try
//        {
//            if (lastShow != prefs.getInt(SHOWED, -1))
//            {
//                // 修改显示日期
//                editor.putInt(SHOWED, lastShow);
//                editor.commit();
//                return true;
//            }
//        }
//        catch (Exception e)
//        {
//            try
//            {
//                editor.remove(SHOWED);
//                editor.commit();
//            }
//            catch (Exception e1)
//            {
//                e1.printStackTrace();
//                return false;
//            }
//            e.printStackTrace();
//            return false;
//        }
//        return false;
//    }

    public void startAnimation()
    {

        // 一天只显示一次
//        if (!needPlayerAnimation() || AccountPreferences.getLogin(mContext))
//        {
//            return;
//        }
        // 初始化动画,
        if (interpolator == null)
        {
            interpolator = new LinearInterpolator();
        }
        // 初始化文本动画
        if (textAnimation == null)
        {
            textAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.register_button_shake);
            textAnimation.setInterpolator(interpolator);
            textAnimation.setAnimationListener(this);
        }
        // 初始化背景动画
        if (backgroundAnimation == null)
        {
            backgroundAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.register_button_alpha_and_scale);
            backgroundAnimation.setInterpolator(interpolator);
            backgroundAnimation.setAnimationListener(this);
        }
        // 动画正在运行先停止正在运行的动画
        if (isRunning())
        {
            cancelAnimation();
        }
        isRun = true;
        // 开始动画
        textView.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                textView.startAnimation(textAnimation);
            }
        }, 1000);
    }

    @Override
    public void onAnimationStart(Animation animation)
    {

    }

    @Override
    public void onAnimationEnd(Animation animation)
    {
        // textAnimation结束后播放imageAnimation动画
        if (animation == textAnimation)
        {
            imageView.setVisibility(VISIBLE);
            imageView.startAnimation(backgroundAnimation);
        }
        else if (animation == backgroundAnimation)
        {
            reset();
            imageView.setVisibility(GONE);
            isRun = false;
        }
    }

    private void reset()
    {
        imageView.setAlpha(1);
        imageView.setScaleX(1);
        imageView.setScaleY(1);
        textView.setScaleX(1);
        textView.setScaleY(1);
        textView.setAlpha(1);
    }

    /**
     * 动画是否运行
     * 
     * @return isRun
     */
    public boolean isRunning()
    {
        return isRun;
    }

    @Override
    public void onAnimationRepeat(Animation animation)
    {

    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        cancelAnimation();
    }

    public void cancelAnimation()
    {
        // 动画尚未结束的时候需要关闭动画
        if (textAnimation != null)
        {
            textView.clearAnimation();
        }
        if (backgroundAnimation != null)
        {
            imageView.clearAnimation();
            imageView.setVisibility(GONE);
        }
        isRun = false;
    }
}
