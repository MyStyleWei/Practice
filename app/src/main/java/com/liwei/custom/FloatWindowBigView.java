package com.liwei.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.weili.practice.R;

/**
 * Created by wei.li on 2015/11/6.
 */
public class FloatWindowBigView extends LinearLayout {

    /**
     * 记录大悬浮窗的宽度
     */
    public static int viewWidth;

    /**
     * 记录大悬浮窗的高度
     */
    public static int viewHeight;

    public FloatWindowBigView(Context context) {
        this(context,null);
    }

    public FloatWindowBigView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FloatWindowBigView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }



    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.float_window_big,null);
        View view = findViewById(R.id.big_window_layout);

        viewHeight = view.getLayoutParams().height;
        viewWidth = view.getLayoutParams().width;

        Button close = (Button) view.findViewById(R.id.close);
        Button open = (Button) view.findViewById(R.id.back);

        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        open.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }



}
