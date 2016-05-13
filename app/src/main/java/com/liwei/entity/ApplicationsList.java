package com.liwei.entity;

import java.util.List;

/**
 * Created by wei.li on 2016/2/24.
 */
public class ApplicationsList {

    private static ApplicationsList list = new ApplicationsList();

    private List<AppInfo> appInfos;

    public List<AppInfo> getAppInfos() {
        return appInfos;
    }

    public void setAppInfos(List<AppInfo> appInfos) {
        this.appInfos = appInfos;
    }

    private ApplicationsList(){

    }

    public static ApplicationsList getInstance(){
        return list;
    }
}
