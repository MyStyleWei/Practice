package com.liwei.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wei.li on 2015/10/29.
 */
public class SweepGradientView extends View {


    private Paint mPaint;
    private Shader shader;

    public SweepGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public SweepGradientView(Context context) {
        super(context);
        initView();
    }

    void initView(){
        mPaint = new Paint();
        shader = new SweepGradient(240, 360, new int[] {Color.CYAN,Color.DKGRAY,Color.GRAY,Color.LTGRAY,Color.MAGENTA,
                Color.GREEN,Color.TRANSPARENT, Color.BLUE }, null);
        mPaint.setShader(shader);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
     //   canvas.drawCircle(240, 360, 200, mPaint);
        RectF rectF = new RectF(40,160,440,560);
        canvas.drawArc(rectF,240, 360,false,mPaint);
    }
}
