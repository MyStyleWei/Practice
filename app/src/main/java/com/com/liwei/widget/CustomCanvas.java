package com.com.liwei.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wei.li on 2016/2/1.
 */
public class CustomCanvas extends View {

    private Paint backgroundPaint;
    private Paint linePaint;


    public CustomCanvas(Context context) {
        this(context,null);
    }

    public CustomCanvas(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setColor(Color.parseColor("#828282"));

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.parseColor("#00CD00"));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int px = getMeasuredWidth();

        int py = getMeasuredWidth();

// Draw background

        canvas.drawRect(0, 0, px, py, backgroundPaint);


        canvas.rotate(90, px/2, py/2);

        canvas.drawCircle(px / 2-10,10,5,linePaint );
        canvas.drawLine(px / 4, 0, 0, py / 2, linePaint);

//        canvas.drawLine(px / 2, 0, px, py / 2, linePaint);
//
//        canvas.drawLine(px / 2, 0, px / 2, py, linePaint);


        canvas.drawCircle(px - 10, py - 10, 10, linePaint);

// Draw circle


    }
}
