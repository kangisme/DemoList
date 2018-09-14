package com.kang.animation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author created by Administrator on 2018/9/13 0013 15:41
 */
public class TranslateAnim extends Animation {
    private float mFromX;
    private float mFromY;
    private float mToX;
    private float mToY;

    public TranslateAnim(float fromX, float fromY, float toX, float toY) {
        mFromX = fromX;
        mFromY = fromY;
        mToX = toX;
        mToY = toY;
    }
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        //不需要初始化操作
    }
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float dx = mFromX;
        float dy = mFromY;
        //根据逝去时间百分比计算当前坐标
        if (dx != mToX) {
            dx = mFromX + (mToX - mFromX) * interpolatedTime;
        }
        if (dy != mToY) {
            dy = mFromY + (mToY - mFromY) * interpolatedTime;
        }
        t.getMatrix().setTranslate(dx, dy);
    }
}

