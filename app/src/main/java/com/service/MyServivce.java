package com.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jni.IMyAInterface;

/**
 * Created by wei.li on 2016/4/29.
 */
public class MyServivce extends Service {

    private static final String TAG= "MySevice....";

    private IBinder mBinder = new IMyAInterface.Stub(){
        @Override
        public String getInfo(String s) throws RemoteException {
            Log.e(TAG,"getInfo......"+s);
            return "我是serve返回的字符串";
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return mBinder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"onCreate......");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"onStartCommand......"+intent.getStringExtra("111"));
        return super.onStartCommand(intent, flags, startId);
    }
}
