package com.jni;

import android.os.Binder;
import android.os.IBinder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Created by wei.li on 2016/2/29.
 */
public class BinderHookHelper {


    public static void hookClipboardService() throws Exception {
        final String CLIPBOARD_SERVICE = "clipboard";
        Class<?> clazz = Class.forName("android.os.ServiceManager");
        Method getService = clazz.getDeclaredMethod("getService",String.class);
        IBinder iBinder = (IBinder) getService.invoke(null,CLIPBOARD_SERVICE);

        IBinder hookIBinder = (IBinder) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class<?>[]{IBinder.class},new BinderProxyHookHandler(iBinder));


        Field cacheField = clazz.getDeclaredField("sCache");
        cacheField.setAccessible(true);
        Map<String,IBinder> cache = (Map) cacheField.get(null);
        cache.put(CLIPBOARD_SERVICE,hookIBinder);
    }
}
