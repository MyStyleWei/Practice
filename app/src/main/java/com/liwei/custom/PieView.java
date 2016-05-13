package com.liwei.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.liwei.entity.PieData;

import java.util.ArrayList;

/**
 * Created by wei.li on 2016/3/11.
 */
public class PieView extends View {

    // 颜色表
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    // 数据
    private ArrayList<PieData> mData;
    // 宽高
    private int mWidth, mHeight;
    // 画笔
    private Paint mPaint = new Paint();

    public PieView(Context context) {
        this(context,null);
    }

    public PieView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }


    void initPaint(){
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    public void setmStartAngle(float mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();
    }

    public void setmData(ArrayList<PieData> mData) {
        this.mData = mData;
        initDatas(mData);
        invalidate();
    }

    private void initDatas(ArrayList<PieData> mData){
        if(mData == null || mData.size() == 0) return;

        float sumValue = 0;
        for(int i = 0;i<mData.size();i++){
            PieData pie = mData.get(i);
            sumValue += pie.getValue();
            int j = i%mColors.length;
            pie.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for(int i = 0;i<mData.size();i++){
            PieData pie = mData.get(i);
            float percentage = pie.getValue()/sumAngle;
            float angle = percentage * 360;
            pie.setPercentage(percentage);
            pie.setAngle(angle);

            sumAngle += angle;

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(null == mData)  return;

        float currentStartAngle = mStartAngle;
        canvas.translate(mWidth/2,mHeight/2);

        float r = (float)(Math.min(mWidth,mHeight)/2 *0.8);
        RectF f = new RectF(-r,-r,r,r);
        for(int i = 0;i<mData.size();i++){
            PieData pie = mData.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(f,currentStartAngle,pie.getAngle(),true,mPaint);
            currentStartAngle += pie.getAngle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
    }
}
