package com.kang.toolbar;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class BackButton extends android.support.v7.widget.AppCompatImageView
{
    public BackButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        init();
    }

    private void init()
    {
        setImageResource(R.mipmap.back_button);
        setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    ((Activity) getContext()).onBackPressed();
                }
                catch (Exception e)
                {
                    ((Activity) getContext()).finish();
                }
            }
        });
    }
}
