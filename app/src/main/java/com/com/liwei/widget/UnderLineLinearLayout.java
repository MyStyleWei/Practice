package com.com.liwei.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

import com.example.weili.practice.R;

/**
 * Created by wei.li on 2016/1/25.
 */
public class UnderLineLinearLayout extends LinearLayout {

    private Bitmap mIcon;
    private int lineMarginSide;
    private int lineDynamicDimen;
    private int lineStrokeWidth;
    private int lineColor;
    private int pointSize;
    private int pointColor;

    private Paint linePaint;
    private Paint pointPaint;

    private int firstX;
    private int firstY;

    private int lastX;
    private int lastY;

    private int defOrientation = VERTICAL;
    private Context context;

    private boolean drawLine = true;

    public UnderLineLinearLayout(Context context) {
        this(context,null);
    }

    public UnderLineLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public UnderLineLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UnderLineLinearLayout);
//        int c = a.getIndexCount();


        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.UnderLineLinearLayout);
        lineMarginSide = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_margin_side, 10);
        lineDynamicDimen = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_dynamic_dimen, 0);
        lineStrokeWidth = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_stroke_width, 2);
        lineColor = attr.getColor(R.styleable.UnderLineLinearLayout_line_color, 0xff3dd1a5);
        pointSize = attr.getDimensionPixelSize(R.styleable.UnderLineLinearLayout_point_size, 8);
        pointColor = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_point_color, 0xff3dd1a5);
//        for(int i = 0;i<c;i++){
//            switch (a.getIndex(i)){
//                case R.styleable.UnderLineLinearLayout_icon_src:
//                    int iconRes = a.getResourceId(R.styleable.UnderLineLinearLayout_icon_src,R.drawable.ic_ok);
//                    BitmapDrawable drawable = (BitmapDrawable) context.getResources().getDrawable(iconRes);
//                    if(drawable != null) mIcon = drawable.getBitmap();
//                    break;
//                case R.styleable.UnderLineLinearLayout_line_color:
//                    lineColor = a.getColor(R.styleable.UnderLineLinearLayout_line_color,0xff3dd1a5);
//                    break;
//                case R.styleable.UnderLineLinearLayout_line_dynamic_dimen:
//                    lineDynamicDimen = a.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_dynamic_dimen,0);
//                    break;
//                case R.styleable.UnderLineLinearLayout_line_margin_side:
//                    lineMarginSide = a.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_margin_side,10);
//                    break;
//                case R.styleable.UnderLineLinearLayout_line_stroke_width:
//                    lineStrokeWidth = a.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_stroke_width,2);
//                    break;
//                case R.styleable.UnderLineLinearLayout_point_color:
//                    pointColor = a.getColor(R.styleable.UnderLineLinearLayout_point_color,0xff3dd1a5);
//                    break;
//                case R.styleable.UnderLineLinearLayout_point_size:
//                    pointSize = a.getDimensionPixelSize(R.styleable.UnderLineLinearLayout_point_size,8);
//                    break;
//                default:
//                    break;
//            }
//        }

        int iconRes = attr.getResourceId(R.styleable.UnderLineLinearLayout_icon_src, R.drawable.ic_ok);
        BitmapDrawable temp = (BitmapDrawable) context.getResources().getDrawable(iconRes);
        if (temp != null) mIcon = temp.getBitmap();
        defOrientation = getOrientation();
        attr.recycle();
        initView(context);
    }

    private void initView(Context context){
        this.context = context;

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setDither( true);
        linePaint.setColor(lineColor);
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        linePaint.setStrokeWidth(lineStrokeWidth);

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setDither(true);
        pointPaint.setColor(pointColor);
        pointPaint.setStyle(Paint.Style.FILL);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(drawLine){
            drawTimeLine(canvas);
        }
    }
    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
        this.defOrientation=orientation;
    }


    private void drawTimeLine(Canvas canvas){

        int childCount = getChildCount();

        if(childCount > 0){
            switch (defOrientation){
                case VERTICAL:
                    drawFirstChildView(canvas);
                    drawLastChildView(canvas);
                    drawBetweenChildView(canvas);
                    break;
                default:
                    break;
            }
        }
    }


    private void drawFirstChildView(Canvas canvas){
        if(getChildAt(0) != null){
            int top = getChildAt(0).getTop();
            //记录值
            firstX = lineMarginSide;
            firstY = top +getChildAt(0).getPaddingTop()+lineDynamicDimen;
            canvas.drawCircle(firstX,firstY,pointSize,pointPaint);
        }
    }


    private void drawLastChildView(Canvas canvas){
        if(getChildAt(getChildCount() -1) != null){
            int top = getChildAt(getChildCount() -1).getTop();
            //记录值
            lastX = lineMarginSide - (mIcon.getWidth() >> 1 );
            lastY = top +getChildAt(getChildCount()-1).getPaddingTop()+lineDynamicDimen;
            canvas.drawBitmap(mIcon,lastX,lastY,null);
        }
    }

    private void drawBetweenChildView(Canvas canvas){
        for(int i = 0;i<getChildCount() -1;i++){
            canvas.drawLine(lineMarginSide,firstY,lineMarginSide,lastY,linePaint);
            if(getChildAt(i) != null && i != 0){
                int top = getChildAt(i).getTop();
                int y = top + getChildAt(i).getPaddingTop()+lineDynamicDimen;
                canvas.drawCircle(lineMarginSide,y,pointSize,pointPaint);
            }
        }
    }
}
