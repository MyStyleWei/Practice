package com.application;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by wei.li on 2015/10/30.
 */
public class MyApplication extends Application{


    private RefWatcher mRefWatcher;
    public static Context context;
    @Override
    public void onCreate() {
        Fresco.initialize(this);
        super.onCreate();

        context = this;
        mRefWatcher = LeakCanary.install(this);
        Toast.makeText(getApplicationContext(),"Regeg",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();



    }

    public static RefWatcher getRefWatcher(Context context){
        MyApplication application = (MyApplication) context.getApplicationContext();
        return  application.mRefWatcher;
    }
}
