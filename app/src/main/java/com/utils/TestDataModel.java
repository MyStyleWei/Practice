package com.utils;

import android.widget.TextView;

/**
 * Created by wei.li on 2015/10/30.
 */
public class TestDataModel {

    private static TestDataModel sInstance;
    private TextView mRetainedTextView;

    public static TestDataModel getInstance(){
        if(sInstance == null) {
            sInstance = new TestDataModel();

        }

        return sInstance;
    }

    public void setRetainedTextView(TextView textView){
        mRetainedTextView = textView;
    }

}
