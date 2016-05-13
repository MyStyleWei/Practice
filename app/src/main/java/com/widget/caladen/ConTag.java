package com.widget.caladen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wei.li on 2016/4/25.
 */
public class ConTag extends ViewGroup {

    public ConTag(Context context) {
        this(context,null);
    }

    public ConTag(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ConTag(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineHeight;
        int lineCount;
        int childCount = getChildCount();
        for(int i = 0;i<childCount;i++){
            View  child = getChildAt(i);

            for(int j=0;j<3;j++){
                child.layout(l*j,t,r,b);
            }


        }
    }
}
