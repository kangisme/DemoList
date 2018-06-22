package com.kang.index;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * @author created by kangren on 2018/6/22 17:54
 */
public class StickyLayout extends LinearLayout
{

    Context mContext;

    // 布局
    LinearLayout mTopView;

    LinearLayout mIndicatorView;

    RecyclerView mBottomView;

    // 滚动相关属性
    int touchSlop;

    OverScroller mOverScroller;

    VelocityTracker mVelocityTracker;

    int maxFling;

    int minFling;

    // 顶部view的高度，此处用来作为临界点的值
    int mTopViewHeight;

    // ListView内容总高度
    int contentHeight = 0;

    // ListView初始化高度
    int startListViewHeight = 0;

    // 悬浮view是否已经进入悬浮状态
    boolean isTopHidden = false;

    int lastY;

    // 是否已经处理过临界点直接更改dispatchTouchEvent
    boolean isInControl = false;

    public StickyLayout(Context context)
    {
        this(context, null);
    }

    public StickyLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        init(context, attrs);
    }

    public static int dp2px(Context context, float dp)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private void init(Context context, AttributeSet attrs)
    {
        this.mContext = context;

        // 设置排列方向
        setOrientation(VERTICAL);

        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        maxFling = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        minFling = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();

        mOverScroller = new OverScroller(context);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();

        mTopView = (LinearLayout) findViewById(R.id.id_topview);
        mIndicatorView = (LinearLayout) findViewById(R.id.id_indicatorview);
        mBottomView = (RecyclerView) findViewById(R.id.id_bottomview);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 初始化ListView的高度
        startListViewHeight = getMeasuredHeight() - mIndicatorView.getMeasuredHeight()
                - mTopView.getMeasuredHeight();

        // listview在可以悬浮时的高度应该为 总高度-导航条高度
        LayoutParams params = (LayoutParams) mBottomView.getLayoutParams();
        params.height = getMeasuredHeight() - mIndicatorView.getMeasuredHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        mTopViewHeight = mTopView.getMeasuredHeight();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        int y = (int) ev.getY();
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // 由下往上滑动时候切换
                // 获取可见范围内第一个view
                int position = 0;
                RecyclerView.LayoutManager layoutManager = mBottomView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager)
                {
                    position = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
                }
                View viewItem = mBottomView.getChildAt(position);
                if (isTopHidden && (y - lastY) > 0 && viewItem != null && viewItem.getTop() == 0 && !isInControl)
                {
                    isInControl = true;
                    ev.setAction(MotionEvent.ACTION_CANCEL);
                    MotionEvent event2 = MotionEvent.obtain(ev);
                    dispatchTouchEvent(ev);
                    event2.setAction(MotionEvent.ACTION_DOWN);
                    return dispatchTouchEvent(event2);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        int y = (int) ev.getY();
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(y - lastY) > touchSlop)
                {
                    // 获取可见范围内第一个view
                    int position = 0;
                    RecyclerView.LayoutManager layoutManager = mBottomView.getLayoutManager();
                    if (layoutManager instanceof LinearLayoutManager)
                    {
                        position = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
                    }
                    View viewItem = mBottomView.getChildAt(position);
                    // viewGroup拦截条件：
                    // 1. 顶部待隐藏view未隐藏
                    // 2. 顶部待隐藏view已经隐藏，当前操作界面是listview的时候，往上滚到达临界点
                    if (!isTopHidden || (isTopHidden && (y - lastY) > 0 && viewItem != null && position == 0
                            && viewItem.getTop() == 0))
                    {
                        initVelocityTrackerIfNotExists();
                        mVelocityTracker.addMovement(ev);
                        lastY = y;
                        return true;
                    }
                    lastY = y;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                recycleVelocityTracker();
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        initVelocityTrackerIfNotExists();
        mVelocityTracker.addMovement(event);
        int y = (int) event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if (!mOverScroller.isFinished())
                {
                    mOverScroller.abortAnimation();
                }
                lastY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                scrollBy(0, -(y - lastY));
                // 由上往下滑动时候切换
                if (getScrollY() == mTopViewHeight && (y - lastY) < 0)
                {
                    event.setAction(MotionEvent.ACTION_DOWN);
                    dispatchTouchEvent(event);
                    isInControl = false;
                }
                lastY = y;
                break;
            case MotionEvent.ACTION_CANCEL:
                recycleVelocityTracker();
                if (!mOverScroller.isFinished())
                {
                    mOverScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_UP:
                // 满足最小滑动条件，UP事件执行滑动操作
                mVelocityTracker.computeCurrentVelocity(1000, maxFling);
                int yVelocity = (int) mVelocityTracker.getYVelocity();
                if (Math.abs(yVelocity) > minFling)
                {
                    fling(yVelocity);
                }
                recycleVelocityTracker();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void fling(int yVelocity)
    {
        mOverScroller.fling(0, getScrollY(), 0, -yVelocity, 0, 0, 0, mTopViewHeight);
        invalidate();
    }

    @Override
    public void scrollTo(int x, int y)
    {
        // 限定滚动在一定范围内
        if (y < 0)
        {
            y = 0;
        }
        // 内容高度不满足滑动条件时拒绝滑动
        if (contentHeight < startListViewHeight)
        {
            y = 0;
        }
        // 滚动距离不满足时候不能超过差值
        if (y > (contentHeight - startListViewHeight) && (contentHeight - startListViewHeight) > 0)
        {
            y = contentHeight - startListViewHeight;
        }
        // 滚动距离不能超过悬浮栏
        if (y > mTopViewHeight)
        {
            y = mTopViewHeight;
        }
        if (y != getScrollY())
        {
            super.scrollTo(x, y);
        }

        // 如果滚动距离与顶部view的高度一致，滚动距离达到最大，悬浮栏悬浮
        isTopHidden = getScrollY() == mTopViewHeight;
    }

    @Override
    public void computeScroll()
    {
        super.computeScroll();
        if (mOverScroller.computeScrollOffset())
        {
            scrollTo(0, mOverScroller.getCurrY());
            invalidate();
        }
    }

    private void initVelocityTrackerIfNotExists()
    {
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker()
    {
        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    public void setContentHeight(int item)
    {
        this.contentHeight = item * dp2px(mContext, 50);
    }
}
