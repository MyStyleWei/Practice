package com.liwei.entity;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by wei.li on 2016/2/24.
 */
public class AppInfoRich implements Comparable<Object> {

    private String name = null;
    private Context context = null;
    private ResolveInfo mResolveInfo;
    private ComponentName mComponentName = null;
    private PackageInfo pi = null;
    private Drawable icon = null;


    public AppInfoRich(Context context,ResolveInfo ri){
        this.context = context;
        mResolveInfo = ri;
        mComponentName = new ComponentName(ri.activityInfo.applicationInfo.packageName,ri.activityInfo.name);

        try {
            pi = context.getPackageManager().getPackageInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getName(){
        if(name != null){
            return  name;
        }else{
            try {
                return getNameFromResolveInfo(mResolveInfo);
            } catch (PackageManager.NameNotFoundException e) {
                return getPackageName();
            }
        }
    }


    public String getPackageName(){
        return mResolveInfo.activityInfo.packageName;
    }
    public String getActivityNmae(){
        return mResolveInfo.activityInfo.name;
    }


    public Drawable getIcon(){
        if(icon == null){
            icon = getResolveInfo().loadIcon(context.getPackageManager());
        }

        return icon;
    }

    public ResolveInfo getResolveInfo(){
        return mResolveInfo;
    }

    public PackageInfo getPackageInfo(){
        return pi;
    }


    public long getLastUpdateTime(){
        PackageInfo pi = getPackageInfo();

        if(pi != null && Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD){
            return pi.firstInstallTime;
        }else{
            return 0;
        }

    }


    @Override
    public int compareTo(Object another) {
        AppInfoRich infoRich = (AppInfoRich) another;

        return this.getClass().getName().compareTo(infoRich.getClass().getName());
    }

    public String getNameFromResolveInfo(ResolveInfo ri) throws PackageManager.NameNotFoundException {
        String name = ri.resolvePackageName;
        if (ri.activityInfo != null) {
            Resources res = context.getPackageManager().getResourcesForApplication(ri.activityInfo.applicationInfo);
            Resources engRes = getEnglishRessources(res);

            if (ri.activityInfo.labelRes != 0) {
                name = engRes.getString(ri.activityInfo.labelRes);

                if (name == null || name.equals("")) {
                    name = res.getString(ri.activityInfo.labelRes);
                }

            } else {
                name = ri.activityInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
            }
        }
        return name;
    }

    public Resources getEnglishRessources(Resources standardResources) {
        AssetManager assets = standardResources.getAssets();
        DisplayMetrics metrics = standardResources.getDisplayMetrics();
        Configuration config = new Configuration(standardResources.getConfiguration());
        config.locale = Locale.US;
        return new Resources(assets, metrics, config);
    }
}
