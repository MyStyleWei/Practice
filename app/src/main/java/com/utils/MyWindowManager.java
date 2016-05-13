package com.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.view.WindowManager;

import com.liwei.custom.FloatWindowBigView;
import com.liwei.custom.FloatWindowSmallView;

/**
 * Created by wei.li on 2015/11/4.
 */
public class MyWindowManager {

    private static FloatWindowSmallView smallView;
    private static FloatWindowBigView bigView;

    private static WindowManager.LayoutParams smallParams;
    private static WindowManager.LayoutParams bigParams;

    private static WindowManager mWindowManager;

    private static ActivityManager activityManager;



    public static void createSmallWindow(Context context){
        WindowManager windowManager = getWindowManager(context);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        int screenHeight = windowManager.getDefaultDisplay().getHeight();
        if(smallView == null){
            smallView = new FloatWindowSmallView(context);
            if(smallParams == null){
                smallParams = new WindowManager.LayoutParams();

            }
        }

    }



    private static WindowManager getWindowManager(Context context){
        if(mWindowManager == null){
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }
    private static ActivityManager getActivityManager(Context context){
        if(activityManager == null){
            activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        }
        return activityManager;
    }
}
