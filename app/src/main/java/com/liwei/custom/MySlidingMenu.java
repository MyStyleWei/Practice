package com.liwei.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by wei.li on 2016/2/22.
 */
public class MySlidingMenu extends ViewGroup {

    private int mScreenWidth,mScreenHeight;
    private int mMenuRightPadding;
    private ViewGroup mMenu,mContent;
    private int mMenuWidth;
    private int mContentWidth;
    private Scroller mScroller;


    public MySlidingMenu(Context context) {
        this(context,null);
    }

    public MySlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MySlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;
        mMenuRightPadding = convertToDp(context,100);
        System.out.println("mScreenWidth==>>"+mScreenWidth);
        System.out.println("mMenuRightPadding==>>"+mMenuRightPadding);

        mScroller = new Scroller(context);


    }


    private int convertToDp(Context context,int num){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,num,context.getResources().getDisplayMetrics());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mMenu = (ViewGroup) getChildAt(0);
        mContent = (ViewGroup) getChildAt(1);

        mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
        mContentWidth = mContent.getLayoutParams().width = mScreenWidth;

        //测量Menu
        measureChild(mMenu,widthMeasureSpec,heightMeasureSpec);
        //测量Content
        measureChild(mContent,widthMeasureSpec,heightMeasureSpec);
        //测力自己，自己的宽度
        setMeasuredDimension(mMenuWidth+mContentWidth,mScreenHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        mMenu.layout(-mMenuWidth,0,0,mScreenHeight);
        mContent.layout(0,0,mScreenWidth,mScreenHeight);
    }

    private int mLastXIntercept,mLastYIntercept;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) ev.getX() - mLastXIntercept;
                int deltaY = (int) ev.getY() - mLastYIntercept;
                if(Math.abs(deltaX) > Math.abs(deltaY)){
                    //横向滑动
                    intercept = true;
                }else{
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;


        }

        mLastX = x;
        mLastY = y;
        mLastXIntercept = x;
        mLastYIntercept = y;

        return intercept;
    }


    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    public void toggleMenu(){
        if(isOpen){
            closeMenu();
        }else{
            openMenu();
        }
    }

    private void closeMenu(){
        mScroller.startScroll(getScrollX(),0,-getScrollX(),0,500);
        invalidate();
        isOpen =false;
    }

    private void openMenu(){
        mScroller.startScroll(getScrollX(),0,-mMenuWidth-getScrollX(),0,500);
        invalidate();
        isOpen = true;
    }

    private int mLastX,mLastY;
    private boolean isOpen;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                System.out.println("getScrollX==>>"+getScrollX());

                int currentX = (int) event.getX();
                int currentY = (int) event.getY();
                int dx = currentX - mLastX;
                if(dx < 0){//向左滑动
                    if(getScrollX() + Math.abs(dx) >=0){
                        scrollTo(0,0);
                    }else{
                        scrollBy(-dx,0);
                    }

                }else{//向右滑动
                    if(getScrollX() - dx <= -mMenuWidth){
                        scrollTo(-mMenuWidth,0);
                    }else{
                        scrollBy(-dx,0);
                    }

                }

                mLastX = currentX;
                mLastY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                if(getScrollX() < -mMenuWidth/2){//打开menu
                    mScroller.startScroll(getScrollX(),0,-mMenuWidth-getScrollX(),0,300);
                    isOpen = true;
                    invalidate();
                }else{
                    mScroller.startScroll(getScrollX(),0,-getScrollX(),0,300);
                    isOpen = false;
                    invalidate();
                }
                break;
            default:
                break;

        }


        return true;
    }
}
