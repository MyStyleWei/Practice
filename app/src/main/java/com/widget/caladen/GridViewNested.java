package com.widget.caladen;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by wei.li on 2016/4/28.
 */
public class GridViewNested extends GridView {

    public GridViewNested(Context context) {
        this(context,null);
    }

    public GridViewNested(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GridViewNested(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int specHeight = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, specHeight);

    }
}
