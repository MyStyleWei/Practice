package com.liwei.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wei.li on 2015/10/29.
 */
public class Radol extends View{

    Paint mPaint = null;
    // 环形渐变渲染
    Shader mRadialGradient = null;
    public Radol(Context context) {
        super(context);
        //1.圆心X坐标2.Y坐标3.半径 4.颜色数组 5.相对位置数组，可为null 6.渲染器平铺模式
        mRadialGradient = new RadialGradient(240, 240, 240, new int[] {
                Color.YELLOW, Color.GREEN, Color.TRANSPARENT, Color.RED }, null,
                Shader.TileMode.REPEAT);

        mPaint = new Paint();
    }


    public Radol(Context context, AttributeSet attrs) {
        super(context, attrs);
        //1.圆心X坐标2.Y坐标3.半径 4.颜色数组 5.相对位置数组，可为null 6.渲染器平铺模式
        mRadialGradient = new RadialGradient(240, 240, 240, new int[] {
                Color.YELLOW, Color.GREEN, Color.TRANSPARENT, Color.RED },
                new float[]{0.0f,0.5f,0.8f,1.0f},
                Shader.TileMode.MIRROR);

        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制环形渐变
        mPaint.setShader(mRadialGradient);
        // 第一个,第二个参数表示圆心坐标
        // 第三个参数表示半径
        canvas.drawCircle(240, 360, 200, mPaint);

    }
}
