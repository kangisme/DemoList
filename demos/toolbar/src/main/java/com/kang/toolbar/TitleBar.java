package com.kang.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleBar extends RelativeLayout
{
    /**
     * 解析text属性
     */
    public static int[] TEXT_ATTR = {android.R.attr.textStyle, android.R.attr.text};


    /**
     * 标题textView
     */
    private TextView mTitleTv = null;

    private BackButton backButton;

    public TitleBar(Context context)
    {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initView();
        try
        {
            TypedArray ta = context.obtainStyledAttributes(attrs, TEXT_ATTR);
            setText(ta.getText(1));
            int style = ta.getInt(0, 0);
            setTextStyle(style);
            ta.recycle();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 初始化界面元素
     */
    private void initView()
    {
        if (isInEditMode())
        {
            return;
        }
        setBackgroundColor(Color.parseColor("#FAFAFA"));
        LayoutInflater.from(getContext()).inflate(R.layout.title_bar_layout, this, true);

        mTitleTv = (TextView) findViewById(R.id.title_bar_title);
        mTitleTv.setSelected(true);
        backButton = (BackButton) findViewById(R.id.title_bar_back);
    }

    /**
     * 设置标题style
     * @param style {@link Typeface#BOLD}
     */
    private void setTextStyle(int style) {
        Typeface typeface = Typeface.defaultFromStyle(style);
        if (mTitleTv != null)
        {
            mTitleTv.setTypeface(typeface);
        }
    }

    /**
     * 设置标题
     */
    public void setText(CharSequence title)
    {
        if (mTitleTv != null)
        {
            mTitleTv.setText(title);
        }
    }

    /**
     * 设置标题颜色
     */
    public void setTextColor(int color)
    {
        if (mTitleTv != null)
        {
            mTitleTv.setTextColor(color);
        }
    }

    /**
     * 给titleBar 设置背景
     */
    public void setBackground(int id)
    {
        setBackgroundResource(id);
    }

    /**
     * 设置标题
     * 
     * @param resId 资源ID
     */
    public void setText(int resId)
    {
        if (mTitleTv != null)
        {
            if (resId == 0)
            {
                mTitleTv.setText("");
                return;
            }
            mTitleTv.setText(resId);
        }
    }

    /**
     * 设置返回按钮的可见性
     * 
     * @param visibility 可见性
     */
    public void setBackViewVisibility(int visibility)
    {
        View back = findViewById(R.id.title_bar_back);
        if (back != null)
        {
            back.setVisibility(visibility);
        }
    }

    public void setOnBackClickListener(OnClickListener listener)
    {
        backButton.setOnClickListener(listener);
    }
}
