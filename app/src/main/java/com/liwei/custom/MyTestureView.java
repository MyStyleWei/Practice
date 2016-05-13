package com.liwei.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TextureView;

import com.example.weili.practice.R;

import java.util.Random;

/**
 * Created by wei.li on 2016/1/13.
 */
public class MyTestureView extends TextureView implements TextureView.SurfaceTextureListener{


    private Random random = new Random();
    private Rect rect;
    private Paint paint;

    public MyTestureView(Context context) {
        this(context,null);
    }

    public MyTestureView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void setParams(){
        this.setSurfaceTextureListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_find_team);
        DrawingThread thread = new DrawingThread(surface,bitmap);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){

        }

        return super.onTouchEvent(event);
    }

    class  DrawingThread extends HandlerThread implements Handler.Callback{

        private SurfaceTexture surfaceTexture;

        private Canvas canvas;
        private Handler handler;
        private Bitmap bitmap;
        DwaringItem [] mLocations = new DwaringItem[5];

        private static final int MSG_ADD = 100;
        private static final int MSG_MOVE = 101;
        private static final int MSG_CLEAR = 102;

        public DrawingThread(SurfaceTexture surfaceTexture,Bitmap bitmap){
            super("DrawingThread");

            this.surfaceTexture = surfaceTexture;
            this.bitmap = bitmap;


            for(DwaringItem item : mLocations){

            }



            canvas = lockCanvas();


            unlockCanvasAndPost(canvas);

        }

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case MSG_ADD:

                    break;
                case MSG_MOVE:

                    break;
                case MSG_CLEAR:

                    break;
            }
            return false;
        }

        @Override
        public boolean quit() {

            handler.removeCallbacksAndMessages(null);

            return super.quit();
        }

        class DwaringItem{
            private int x,y;
            private boolean isHorizantal,isVertical;

            public DwaringItem(int x,int y,boolean isHorizantal,boolean isVertical){

                this.x = x;
                this.y = y;
                this.isHorizantal = isHorizantal;
                this.isVertical = isVertical;
            }
        }



    }
}
