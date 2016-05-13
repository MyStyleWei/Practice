package com.com.liwei.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wei.li on 2016/1/15.
 */
public class ExpandTextView extends TextView implements View.OnClickListener {


    private int maxLine ;
    private boolean isSingLine = true;
    private boolean isFirstMeasure = true;

    public ExpandTextView(Context context) {
        this(context,null);
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(isFirstMeasure){
            maxLine = getLineCount();
            this.setEllipsize(TextUtils.TruncateAt.END);
            setSingleLine();
            isFirstMeasure = false;
        }
    }

    @Override
    public void onClick(View v) {

        ValueAnimator animator;
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) getLayoutParams();
        if(isSingLine){
            setSingleLine(false);
            animator = ValueAnimator.ofInt(getLineHeight(),maxLine *getLineHeight());
        }else{
            animator = ValueAnimator.ofInt(maxLine *getLineHeight(),getLineHeight());
        }

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                params.height = (int)animation.getAnimatedValue();
                setLayoutParams(params);
            }
        });

        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(300);
        animator.setTarget(this);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(!isSingLine){
                    setSingleLine();
                }

                isSingLine = !isSingLine;
            }
        });
    }
}
