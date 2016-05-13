package com.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wei.li on 2016/1/15.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final  String TAG="CrashHandler";
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private static CrashHandler INSTANCE = new CrashHandler();

    private Context mContext;
    private Map<String,String> infos = new HashMap<String, String>();

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

    private CrashHandler(){

    }

    public static CrashHandler getInstance(){
        return INSTANCE;
    }

    public void init(Context context){
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        if(!handleException(ex) && mDefaultHandler != null){
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread,ex);
        }else{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex){
        if(ex == null){
            return  false;
        }

        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext,"很抱歉,程序出现异常,即将退出.",Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        collectDeviceInfo(mContext);
        saveCrashInfo2File(ex);

        return true;
    }

    /**
     * 收集设备参数信息
     * @param
     */
    public void collectDeviceInfo(Context context){

        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(),PackageManager.GET_ACTIVITIES);
            if(pi != null){
                String versionName = pi.versionName == null?"null":pi.versionName;
                String versioncode = pi.versionCode +"";
                infos.put("versionName",versionName);
                infos.put("versioncode",versioncode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Field[] fields = Build.class.getDeclaredFields();
            for(Field field :fields){
                field.setAccessible(true);
                try {
                    infos.put(field.getName(),field.get(null).toString());
                    Log.d(TAG, field.getName() + " : " + field.get(null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
    }

    private String saveCrashInfo2File(Throwable ex){
        StringBuffer sb = new StringBuffer();
        for(Map.Entry<String,String> entry:infos.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key+"="+value +"\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);

        Throwable cause = ex.getCause();
        while(cause != null){
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        printWriter.close();
        String result = writer.toString();

        sb.append(result);
        try {
        long timestamp = System.currentTimeMillis();
        String time = format.format(new Date());
        String fileName = "crash-"+time+"-"+timestamp+".log";
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            String path = "/sdcard/crash";
            File dir = new File(path);
            if(!dir.exists()){
                dir.mkdirs();
            }
            FileOutputStream fos = null;

                fos = new FileOutputStream(path+fileName);

            fos.write(sb.toString().getBytes());
            fos.close();
        }
        return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }

        return null;
    }


}
