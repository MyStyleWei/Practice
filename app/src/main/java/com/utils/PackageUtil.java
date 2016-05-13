package com.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by wei.li on 2016/4/12.
 */
public class PackageUtil {

    @SuppressWarnings("WrongConstant")
    public static void getPack(Context context){
        PackageManager manager = context.getPackageManager();

        List<ApplicationInfo> infos = manager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        for(ApplicationInfo info :infos){

        }



        ApplicationInfo info = context.getApplicationInfo();
    }
}
