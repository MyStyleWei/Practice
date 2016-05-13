package com.widget.caladen;

import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

/**
 * Created by wei.li on 2016/1/14.
 */
public abstract class MonthAdapter {


    public abstract View createCellView(ViewGroup viewGroup, int position);

    public abstract void bindCellView(ViewGroup viewGroup, View child, int position, Calendar calendar);
}
