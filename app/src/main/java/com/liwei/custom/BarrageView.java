package com.liwei.custom;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.weili.practice.R;
import java.util.Random;

/**
 * Created by wei.li on 2015/10/22.
 */
public class BarrageView extends TextView {

    private Paint paint = new Paint();
    private Random random = new Random();
    private int textSize = 30;
    public static final int TEXT_MIN = 12;
    public static final int TEXT_MAX = 60;

    private int color = 0xffffffff;

    private int windowWidth; //屏幕宽
    private int windowHeight; //屏幕高

    private int posX; //x坐标
    private int posY = textSize; //y坐标

    private Thread rollThread;

    public BarrageView(Context context) {
        super(context);
        initView();
    }

    public BarrageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }


    private  void initView(){
        textSize = TEXT_MIN + random.nextInt(TEXT_MAX - TEXT_MIN);
        paint.setTextSize(textSize);

        color = Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256));
        paint.setColor(color);

        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        windowHeight = rect.height();
        windowWidth = rect.width();

        //设置x为屏幕宽
        posX = windowWidth;
        posY = textSize + random.nextInt(windowHeight - textSize);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(getShowText(),posX,posY,paint);
        if(rollThread == null){
            rollThread = new RollThread();
            rollThread.start();
        }
    }

    private String getShowText(){
        if(getText() != null && !getText().toString().isEmpty()){
            return getText().toString();
        }else{
            return getResources().getString(R.string.default_text);
        }
    }

    private void startAnim(){

    }


    class RollThread extends Thread{
        @Override
        public void run() {
            while (true){
                animLogic();
                postInvalidate();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(needStopRollThread()){
                    if (listener != null){
                        listener.onRollEnd();
                    }
                    post(new Runnable() {
                        @Override
                        public void run() {
                            ((ViewGroup)BarrageView.this.getParent()).removeView(BarrageView.this);
                        }
                    });
                    break;
                }
            }
        }
    }

    private boolean needStopRollThread(){
        if(posX < -paint.measureText(getShowText())){
            return true;
        }
        return false;
    }

    /**
     * 动画逻辑处理
     */
    private void animLogic(){
        posX -= 8;
    }

    /**
     * 滚动结束接听器
     */
    interface OnRollEndListener{
        void onRollEnd();
    }

    private OnRollEndListener listener;
    public void setOnRollEndListener(OnRollEndListener listener){
        this.listener = listener;
    }
}
