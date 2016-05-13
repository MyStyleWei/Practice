package com.jni;

import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wei.li on 2016/2/29.
 */
public class BinderProxyHookHandler implements InvocationHandler{

    private static final String TAG = "BinderProxyHookHandler";
    IBinder mBase;
    Class<?> stub;
    Class<?> iinteerface;

    public BinderProxyHookHandler(IBinder base){
         this.mBase = base;
        try {
            this.stub = Class.forName("ndroid.content.IClipboard$Stub");
            this.iinteerface = Class.forName("android.content.IClipboard");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if("queryLocalInterface".equals(method.getName())){
            Log.d(TAG, "hook queryLocalInterface");

            return Proxy.newProxyInstance(proxy.getClass().getClassLoader(),
                    new Class[]{IBinder.class, IInterface.class,this.iinteerface},
                    new BinderHookHandler(mBase,stub));
        }

        return method.invoke(mBase,args);
    }
}
