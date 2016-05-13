package com;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by wei.li on 2016/3/11.
 */
public abstract  class BasePopWindow extends PopupWindow {


    protected int height,width;
    private int resLayout;
    private Drawable bgd;
    private boolean outToush;
    private int animStyle;
    private OnPopBehavoir onPopBehavoir;

    public BasePopWindow(Context context) {
        this(context,null);
    }

    public BasePopWindow(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BasePopWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.setWidth(width);
        this.setHeight(height);
    }


    public void show(View view){
        this.showAsDropDown(view);
    }




    abstract int setPopHeight(int height);
    abstract void setPopWidth(int height);
    abstract void setPopDissmiss();
    abstract void setPopSow();

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        onPopBehavoir.onPopShow();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        onPopBehavoir.onPopDismiss();
    }

    public interface OnPopBehavoir{
        void onPopShow();
        void onPopDismiss();
    }

    public void setOnPopBehavoir(OnPopBehavoir onPopBehavoir) {
        this.onPopBehavoir = onPopBehavoir;
    }

    public void setResLayout(int resLayout) {
        this.resLayout = resLayout;
    }

    public void setBgd(Drawable bgd) {
        this.bgd = bgd;
    }

    public void setOutToush(boolean outToush) {
        this.outToush = outToush;
    }

    public void setAnimStyle(int animStyle) {
        this.animStyle = animStyle;
    }
}
