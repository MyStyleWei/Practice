package com.liwei.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wei.li on 2015/10/23.
 */
public class Canav extends View {

    private Paint paint = new Paint();

    public Canav(Context context) {
        super(context);

        paint.setAntiAlias(true);

    }

    public Canav(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();//保存画布状态
        paint.setStyle(Paint.Style.FILL);//设置实心
        canvas.clipRect(new Rect(100, 550, 300, 750));//裁剪出一个矩形区域
        canvas.drawColor(Color.LTGRAY);//设置画布颜色
        canvas.drawCircle(150, 600, 100, paint);//在裁剪区域之内，能显示
        canvas.restore();//恢复画布状态

        RectF rectF1 = new RectF(100, 900, 200, 1000);
        canvas.drawArc(rectF1, //弧线所使用的矩形区域大小
                30,  //开始角度
                90, //扫过的角度
                true, //是否使用中心
                paint);
    }
}
