package com.widget.caladen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import android.widget.TextView;

/**
 * Created by wei.li on 2016/1/14.
 */
public class CheckableTextView extends TextView implements Checkable {

    private boolean checked = false;
    private boolean checkable = true;

    private OnCheckedListener onCheckedListener;
//    private Context mContext;
//    Scroller scroller = new Scroller(mContext);
    public CheckableTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    protected void setup() {
        setClickable(true);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    public CheckableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }
//    private void smoothScrollTo(int destX,int dextY){
//        int scrollerX = getScrollX();
//        int deltaX = destX - scrollerX;
//        scroller.startScroll(scrollerX,0,deltaX,dextY,1000);
//        invalidate();
//    }
//
//    @Override
//    public void computeScroll() {
//        if(scroller.computeScrollOffset()){
//            scrollTo(scroller.getCurrX(),scroller.getCurrY());
//            postInvalidate();
//        }
//
//    }

    public CheckableTextView(Context context) {
        super(context);
        setup();
    }



//

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void setChecked(boolean checked) {
        if(! checkable){
            return ;
        }
        this.checked = checked;
        refreshDrawableState();
        if(onCheckedListener != null){
            onCheckedListener.onCheckeListener(this);
        }
    }

    @Override
    public void toggle() {
        setChecked(!checked);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };

    public void setOnCheckedListener(OnCheckedListener onCheckedListener) {
        this.onCheckedListener = onCheckedListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final boolean touchable = super.onTouchEvent(event);
        if(touchable && (event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP){
            toggle();
        }
        return touchable;
    }

    public interface OnCheckedListener{
        void onCheckeListener(Checkable checkable);
    }

    public void setCheckable(boolean checkable) {
        this.checkable = checkable;
    }
    public boolean isCheckable() {
        return checkable;
    }
}
