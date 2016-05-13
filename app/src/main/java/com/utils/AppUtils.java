package com.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;

import com.liwei.entity.AppInfo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by wei.li on 2016/1/20.
 */
public class AppUtils {


    public static int dpToPx(int dp){
        return (int)(dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private static long lastClickTime;
    public synchronized static boolean isFastClick(){
        long time = System.currentTimeMillis();
        if(time - lastClickTime < 5000){
            return true;
        }

        lastClickTime = time;

        return false;
    }


    private Observable<Bitmap> getBitmap(final String icon){
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                subscriber.onNext(BitmapFactory.decodeFile(icon));
                subscriber.onCompleted();
            }
        });
    }


    public static Bitmap drawableToBitmap(Drawable drawable){
        if(drawable instanceof BitmapDrawable){
            return  ((BitmapDrawable) drawable).getBitmap();
        }


    Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    drawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
    drawable.draw(canvas);

    return bitmap;
}

    private Bitmap dr(Drawable drawable){
        if(drawable instanceof BitmapDrawable){
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;

    }

    public static void storeBitmap(final Context context, final Bitmap bitmap, final String fileName){
        Schedulers.io().createWorker().schedule(new Action0() {
            @Override
            public void call() {
                blockingStoreBitmap(context,bitmap,fileName);
            }
        });
    }


    private static void blockingStoreBitmap(Context context,Bitmap bitmap,String fileName){
        FileOutputStream stream = null;

        try {
            stream = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
            stream.flush();
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private boolean  isCurrentlyOnMainThread(){
        return Looper.myLooper() == Looper.getMainLooper();
    }
}