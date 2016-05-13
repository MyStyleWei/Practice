package com.liwei.entity;


import rx.Observable;

/**
 * Created by wei.li on 2016/2/23.
 */

public class AppInfo implements Comparable<Object>{

    long mLastUpdateTime;
    String mName;
    String mIcon;

    public long getmLastUpdateTime() {
        return mLastUpdateTime;
    }

    public void setmLastUpdateTime(long mLastUpdateTime) {
        this.mLastUpdateTime = mLastUpdateTime;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmIcon() {
        return mIcon;
    }

    public void setmIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public AppInfo(long mLastUpdateTime, String mName, String mIcon) {
        this.mLastUpdateTime = mLastUpdateTime;
        this.mName = mName;
        this.mIcon = mIcon;
    }

    @Override
    public int compareTo(Object another) {
        AppInfo appInfo = (AppInfo) another;
        return getClass().getName().compareTo(appInfo.getClass().getName());
    }
}
