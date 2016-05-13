package com.liwei.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.weili.practice.R;
import com.utils.MyWindowManager;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * Created by wei.li on 2015/11/4.
 */
public class FloatWindowSmallView extends LinearLayout {

    private static int viewWidth;
    private static int viewHeight;
    private static int statusHeight;
    private WindowManager windowManager;
    private WindowManager.LayoutParams mParams;
    private float xInScreen;
    private  float yInScreen;
    private float xDownInScreen;
    private float yDownInScreen;
    private float xInView;
    private float yInView;



    public FloatWindowSmallView(Context context) {
        this(context, null);
    }

    public FloatWindowSmallView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatWindowSmallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context){
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater.from(context).inflate(R.layout.float_window_small,this);
        View view = findViewById(R.id.small_layout);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;

        TextView textView = (TextView) view.findViewById(R.id.small_text);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                xInView = event.getX();
                yInView = event.getY();
                xDownInScreen = event.getRawX();
                yDownInScreen = event.getRawY() - getStatusBarHeight();
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                break;
            case MotionEvent.ACTION_MOVE:
                xInScreen  = event.getX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                updateLocation();
                break;
            case MotionEvent.ACTION_UP:
                if(xDownInScreen == xInScreen && yDownInScreen == yInScreen){
                    openBigWindow();
                }
                break;
            default:
                break;

        }


        return true;
    }


    private void openBigWindow(){
    //    MyWindowManager.

    }




    public void  setmParams(WindowManager.LayoutParams params){

    }
    private void updateLocation(){
        mParams.x = (int) (xInScreen - xInView);
        mParams.y = (int) (yInScreen - yInView);
        windowManager.updateViewLayout(this,mParams);
    }

    /**
     * 获取状态兰的高度
     * @return
     */
    private int getStatusBarHeight(){
        if(statusHeight == 0 ){
            Class<?> clazz = null;
            try {
                clazz = Class.forName("com.android.internal.R$dimen");
                Objects obj = (Objects) clazz.newInstance();
                Field field = clazz.getField("status_bar_height");
                int x = (Integer) field.get(obj);
                statusHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return statusHeight;
    }
}
