package com.liwei.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by wei.li on 2016/3/11.
 */
public class PanelPieChartLabel extends View {

    private int ScrWidth,ScrHeight;
    private float rx, ry;


    public PanelPieChartLabel(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

        //解决4.1版本 以下canvas.drawTextOnPath()不显示问题
        this.setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        //屏幕信息
        DisplayMetrics dm = getResources().getDisplayMetrics();
        ScrHeight = dm.heightPixels;
        ScrWidth = dm.widthPixels;
    }

    public void onDraw(Canvas canvas){
        //画布背景
        canvas.drawColor(Color.WHITE);

        //画笔初始化
        Paint PaintArc = new Paint();
        PaintArc.setColor(Color.RED);

        Paint PaintGree = new Paint();
        PaintGree.setColor(Color.GREEN);
        PaintGree.setStyle(Paint.Style.FILL);

        Paint PaintBlue = new Paint();
        PaintBlue.setColor(Color.BLUE);
        PaintBlue.setStyle(Paint.Style.STROKE);

        Paint PaintYellow = new Paint();
        PaintYellow.setColor(Color.YELLOW);
        PaintYellow.setStyle(Paint.Style.FILL);

        //抗锯齿
        PaintArc.setAntiAlias(true);
        PaintYellow.setAntiAlias(true);
        PaintGree.setAntiAlias(true);

        PaintBlue.setTextSize(12);

        float cirX = ScrWidth / 2;
        float cirY = ScrHeight / 3 ;
        float radius = ScrHeight / 5 ;
        //先画个圆确定下显示位置
        canvas.drawCircle(cirX,cirY,radius,PaintGree);

        float arcLeft = cirX - radius;
        float arcTop  = cirY - radius ;
        float arcRight = cirX + radius ;
        float arcBottom = cirY + radius ;
        RectF arcRF0 = new RectF(arcLeft ,arcTop,arcRight,arcBottom);

        ////////////////////////////////////////////////////////////
        //饼图标题
        canvas.drawText("author:xiongchuanliang",60,ScrHeight - 270, PaintBlue);

        //位置计算类
        XChartCalc xcalc = new XChartCalc();

        //实际用于计算的半径
        float calcRadius = radius/2;
        ////////////////////////////////////////////////////////////
        //初始角度
        float pAngle1 = 130f;
        float pAngle2 = 40f;
        float pAngle3 = 360f - pAngle1 - pAngle2;

        //填充扇形
        canvas.drawArc(arcRF0, 0,pAngle1, true,PaintArc);

        //计算并在扇形中心标注上百分比    130%
        xcalc.CalcArcEndPointXY(cirX, cirY, calcRadius, pAngle1/2);
        canvas.drawText(Float.toString(pAngle1)+"%", xcalc.getPosX(),xcalc.getPosY(), PaintBlue);
        //////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////
        //填充扇形
        canvas.drawArc(arcRF0, pAngle1,pAngle2, true,PaintYellow);
        //计算并在扇形中心标注上百分比   40%
        xcalc.CalcArcEndPointXY(cirX, cirY, calcRadius, pAngle1 + pAngle2/2);
        canvas.drawText(Float.toString(pAngle2)+"%", xcalc.getPosX(),xcalc.getPosY(), PaintBlue);

        ////////////////////////////////////////////////////////////
        //计算并在扇形中心标注上百分比  190%
        xcalc.CalcArcEndPointXY(cirX, cirY, calcRadius, pAngle1 + pAngle2 + pAngle3/2);
        canvas.drawText(Float.toString(pAngle3)+"%", xcalc.getPosX(),xcalc.getPosY(), PaintBlue);
        ////////////////////////////////////////////////////////////
    }


}
