package com.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wei.li on 2015/11/4.
 */
public class FloatWindowService extends Service {

    private Handler handler = new Handler();
    private Timer timer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;



    }

    class RefreshTask extends TimerTask{
        @Override
        public void run() {

        }
    }

    /**
     * 判断当前界面是否是桌面
     */
    private boolean isHome(){
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfos = manager.getRunningTasks(1);
        return getHomes().contains(taskInfos.get(0).topActivity.getPackageName());
    }
    /**
     * 获得属于桌面的应用的应用包名称
     *
     * @return 返回包含所有包名的字符串列表
     */
    private List<String> getHomes(){
        List<String> names = new ArrayList<String>();
        PackageManager manager = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfos = manager.queryIntentActivities(intent
        ,PackageManager.MATCH_DEFAULT_ONLY);
        for(ResolveInfo resolveInfo : resolveInfos){
            names.add(resolveInfo.activityInfo.packageName);
        }

        return names;
    }


    public class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(timer == null){
            timer =new Timer();
            timer.scheduleAtFixedRate(new RefreshTask(), 0, 500);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timer = null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
