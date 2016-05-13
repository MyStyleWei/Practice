package com.jni;

import android.os.IBinder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wei.li on 2016/2/29.
 */
public class BinderHookHandler implements InvocationHandler{

    Object base;
    public BinderHookHandler(IBinder base,Class<?> stubClass){

    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
