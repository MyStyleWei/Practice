package com.liwei.custom;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wei.li on 2016/2/19.
 */
public class TDview extends View {


    private int mCenterX;
    private int mCenterY;
    private int mBgColor;
    private float mTouchX;
    private float mTouchY;
    private float mCanvasRotateX = 0;
    private float mCanvasRotateY = 0;
    private float mCanvasMaxRotateDegree = 50;
    private Matrix mMatrix = new Matrix();
    private Camera mCamera = new Camera();
    private Paint mPaint;
    private int mAlpha = 200;
    private double alpha;
    private Path mPath;

    public TDview(Context context) {
        super(context);
    }

    public TDview(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mCanvasMaxRotateDegree = 20;
        mBgColor = Color.parseColor("#227BAE");
        mPath = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(mBgColor);
        mCenterX = getWidth() / 2;
        mCenterY = getHeight() / 2;

        //进行画布的旋转，主要用于小圆点跟随手指移动。
        canvas.rotate((float) alpha,mCenterX,mCenterY);
//
//        alpha = Math.atan((mTouchX-mCenterX)/(mCenterY-mTouchY));
//        alpha = Math.toDegrees(alpha);
//        if(mTouchY>mCenterY){
//            alpha = alpha+180;
//        }
//
//        rotateCanvas(canvas);
        mPaint.setTextSize(30);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);
        canvas.drawText("N",mCenterX,150,mPaint);
        drawArc(canvas);

       drawCircle(canvas);

//        drawPath(canvas);
    }

    private void drawPath(Canvas canvas) {
//        mPaint.setColor(Color.parseColor("#FF3366"));
        mPath.moveTo(mCenterX,293);
        mPath.lineTo(mCenterX-30,mCenterY);
        mPath.lineTo(mCenterX,2*mCenterY-293);
        mPath.lineTo(mCenterX+30,mCenterY);
        mPath.lineTo(mCenterX,293);
        mPath.close();

        canvas.drawPath(mPath,mPaint);
        mPaint.setColor(Color.parseColor("#55227BAE"));
        canvas.drawCircle(mCenterX,mCenterY,20,mPaint);
    }

    private void drawCircle(Canvas canvas) {
        mPaint.setAlpha(255);
        canvas.drawCircle(mCenterX,290,10,mPaint);
    }

    private void drawArc(Canvas canvas) {
        canvas.save();
        for (int i = 0; i < 120; i++) {

            mPaint.setAlpha(255-(mAlpha * i/120));
            canvas.drawLine(mCenterX, 250, mCenterX, 270, mPaint);
            canvas.rotate(3,mCenterX,mCenterY);
        }
        canvas.restore();
    }

    private void rotateCanvas(Canvas canvas) {
        mMatrix.reset();
        mCamera.save();
        mCamera.rotateX(mCanvasRotateX);
        mCamera.rotateY(mCanvasRotateY);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();
        mMatrix.preTranslate(-mCenterX, -mCenterY);
        mMatrix.postTranslate(mCenterX, mCenterY);

        canvas.concat(mMatrix);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        mTouchX = x;
        mTouchY = y;

        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                rotateCanvasWhenMove(x, y);
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                rotateCanvasWhenMove(x, y);
                invalidate();
                return true;
            }
            case MotionEvent.ACTION_UP: {
                mCanvasRotateY = 0;
                mCanvasRotateX = 0;
                invalidate();

                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    private void rotateCanvasWhenMove(float x, float y) {
        float dx = x - mCenterX;
        float dy = y - mCenterY;

        float percentX = dx / mCenterX;
        float percentY = dy / mCenterY;

        if (percentX > 1f) {
            percentX = 1f;
        } else if (percentX < -1f) {
            percentX = -1f;
        }
        if (percentY > 1f) {
            percentY = 1f;
        } else if (percentY < -1f) {
            percentY = -1f;
        }

        mCanvasRotateY = mCanvasMaxRotateDegree * percentX;
        mCanvasRotateX = -(mCanvasMaxRotateDegree * percentY);
    }


//    private Paint paint;
//    private int mCenterX,mCenterY;
//
//    private Camera camera;
//    private Matrix matrix;
//
//    private float mCanvasRotateY = 0 ;
//    private float mCanvasRotateX = 0 ;
//    private float mCanvasMaxRotateDegree = 20;
//
//    public TDview(Context context) {
//        this(context,null);
//    }
//
//    public TDview(Context context, AttributeSet attrs) {
//        this(context, attrs,0);
//    }
//
//    public TDview(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//
//        initView();
//    }
//
//    private void initView(){
//        paint = new Paint();
//        paint.setAntiAlias(true);
//
//        camera = new Camera();
//        matrix = new Matrix();
//    }
//
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        mCenterX = getWidth()/2;
//        mCenterY = getHeight()/2;
//
//        rotateCanvas( canvas);
//
//
//
//
//        canvas.drawCircle(mCenterX,mCenterY,100,paint);
//
//    }
//
//    private void rotateCanvas(Canvas canvas){
//        matrix.reset();
//        camera.save();
//        camera.rotateX(mCanvasRotateX);
//        camera.rotateY(mCanvasRotateY);
//        camera.getMatrix(matrix);
//        camera.restore();
//        //改变矩阵的作用点
//        matrix.preTranslate(-mCenterX,-mCenterY);
//        matrix.postTranslate(mCenterX,mCenterY);
//
//        canvas.concat(matrix);
//    }
//
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        float x = event.getX();
//        float y = event.getY();
//
//        int action = event.getActionMasked();
//        switch (action){
//            case MotionEvent.ACTION_DOWN:
//                rotateCanvasWhenMove(x, y);
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                //这里将camera旋转的变量赋值
//                rotateCanvasWhenMove(x, y);
//                invalidate();
//                return true;
//            case MotionEvent.ACTION_UP:
//                //这里将camera旋转的变量赋值
//                mCanvasRotateY = 0;
//                mCanvasRotateX = 0;
//                invalidate();
//
//                return true;
//
//
//
//        }
//
//        return super.onTouchEvent(event);
//    }
//
//    private void rotateCanvasWhenMove(float x, float y) {
//        float dx = x - mCenterX;
//        float dy = y - mCenterY;
//
//        float percentX = dx / mCenterX;
//        float percentY = dy /mCenterY;
//
//        if (percentX > 1f) {
//            percentX = 1f;
//        } else if (percentX < -1f) {
//            percentX = -1f;
//        }
//        if (percentY > 1f) {
//            percentY = 1f;
//        } else if (percentY < -1f) {
//            percentY = -1f;
//        }
//
//        mCanvasRotateY = mCanvasMaxRotateDegree * percentX;
//        mCanvasRotateX = -(mCanvasMaxRotateDegree * percentY);
//    }

}
