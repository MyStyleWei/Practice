package com.widget.caladen;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wei.li on 2016/1/14.
 */
public abstract class MonthTitleAdapter {


    public abstract View createTitleView(ViewGroup viewGroup);

    public abstract void bindTitleView(View child, int index);

}
