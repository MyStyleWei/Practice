package com.com.liwei.widget;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.Toast;

/**
 * Created by wei.li on 2016/1/27.
 */
public class ScrollerLayout extends ViewGroup {

    private Scroller scroller;
    private int mTouchSlop;
    private float xXDown;
    private float mXMove;
    private float mXLastMove;
    private int leftBorder;
    private int rightBorder;
    private  Context context;


    public ScrollerLayout(Context context) {
        this(context,null);
    }

    public ScrollerLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScrollerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        this.context = context;
        scroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        for(int i = 0;i< childCount;i++){
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        Toast.makeText(context,"width==" +getMeasuredWidth(),Toast.LENGTH_SHORT).show();
        if(changed){
            int childCount = getChildCount();
            for(int i = 0;i< childCount;i++){
                View child = getChildAt(i);
                System.out.println("width===>>>"+child.getMeasuredWidth());
                child.layout(i*child.getMeasuredWidth(),0,(i+1)*getMeasuredWidth(),child.getMeasuredHeight());
            }
             leftBorder = getChildAt(0).getLeft();
             rightBorder = getChildAt(getChildCount()-1).getRight();

        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                xXDown = ev.getRawX();
                mXLastMove = xXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float diff = Math.abs(mXMove - xXDown);
                mXLastMove =mXMove;
                if(diff > mTouchSlop){
                    return true;
                }
                break;
        }


        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scollerX = (int)(mXLastMove - mXMove);
                if(getScaleX() + scollerX < leftBorder){
                    scrollTo(leftBorder,0);
                    return true;
                }else if(getScrollX()+getWidth()+scollerX >rightBorder){
                    scrollTo(rightBorder - getWidth(),0);
                    return  true;
                }

                scrollBy(scollerX,0);
                mXLastMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:

                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                Toast.makeText(context,"getWidth==>"+getWidth(),Toast.LENGTH_SHORT).show();
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                scroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
//                int targetIndex = (getScrollX()+getWidth()/2)/getWidth();
//                int dx = targetIndex * getWidth()-getScrollX();
//                scroller.startScroll(getScrollX(),0,dx,0);
//                invalidate();
                break;

        }


        return super.onTouchEvent(event);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();

        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            invalidate();
        }
    }
}
