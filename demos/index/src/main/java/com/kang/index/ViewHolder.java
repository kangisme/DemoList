package com.kang.index;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * @author created by kangren on 2018/6/22 17:37
 */
public class ViewHolder extends RecyclerView.ViewHolder{

    public TextView mTextView;

    public ViewHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.text);
    }
}