package com.application;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by wei.li on 2015/10/30.
 */
public class MyApplication extends Application{


    private RefWatcher mRefWatcher;
    @Override
    public void onCreate() {
        super.onCreate();

        mRefWatcher = LeakCanary.install(this);
        Toast.makeText(getApplicationContext(),"Regeg",Toast.LENGTH_SHORT).show();
    }


    public static RefWatcher getRefWatcher(Context context){
        MyApplication application = (MyApplication) context.getApplicationContext();
        return  application.mRefWatcher;
    }
}
