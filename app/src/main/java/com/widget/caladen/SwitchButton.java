package com.widget.caladen;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.SoundEffectConstants;
import android.view.View;

import com.example.weili.practice.R;

/**
 * Created by wei.li on 2016/4/29.
 */
public class SwitchButton extends View {

    private static final String TAG = "SwitchButton";
    private static final int PROGRESS_MIN = 0;
    private static final int PROGRESS_MAX = 100;
    private static final int DEFAUT_BOAED_WIDTH = 4;
    private static final int DEFAULT_BOARD_COLOR = 0xFF3c4952;
    private static final int DEFAULT_UNCHECK_REVEAL_COLOR = 0xFF3c4952;
    private static final int DEFAULT_CHECK_REVEAL_COLOR = 0xFFFFC52A;
    private static final int DEFAULT_CHECKED_DRAWABLE_ID = R.drawable.ic_launcher;
    private static final int DEFAULT_UNCHECKED_DRAWABLE_ID = R.drawable.ic_launcher;

    private ArgbEvaluator mEvaluator;

    private int mBoardWidth;
    private int mCheckRevealColor;
    private int mUnCheckRevealColor;
    /**
     * 未选中的时候的资源ID
     */
    private Drawable mUncheckDrawable;
    private static final int DEFAULT_SIZE = 126;
    private boolean mAttachedToWindow;
    /**
     * 未选中的时候的资源ID
     */
    private Drawable mCheckedDrawable;
    private int mProgress;
    private float mFraction;
    private boolean mChecked;

    private Paint mBroadPaint;
    private Paint mCoverPaint;
    private Paint mRevealBgPaint;

    public SwitchButton(Context context) {
        this(context,null);

    }

    public SwitchButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setClickable(true);
        final float density = context.getResources().getDisplayMetrics().density;
        mBoardWidth = (int)(density*DEFAUT_BOAED_WIDTH+0.5);
        initProprity(context,attrs);
        initPaint();
        mEvaluator = new ArgbEvaluator();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float density = getContext().getResources().getDisplayMetrics().density;
        final int defaultSize = (int)(DEFAULT_SIZE *density+0.5);

        int desireWidth = getPaddingLeft()+getPaddingRight()+defaultSize;
        int desireHeight = getPaddingBottom()+getPaddingTop()+defaultSize;
        int widthSpec = resolveSizeAndState(desireWidth,widthMeasureSpec);
        int heightSpec= resolveSizeAndState(desireHeight,heightMeasureSpec);

        super.onMeasure(widthSpec, heightSpec);
    }


    private void setProgress(int progress){
        if(mProgress != progress){
            this.mProgress = progress;
            mFraction = mProgress*1.0f/PROGRESS_MAX;
            invalidate();
        }
    }

    public int getProgress() {
        return mProgress;
    }


    public static long slastTime;
    public boolean onDoubleCkick(){
        boolean flag = false;
        long time = System.currentTimeMillis() - slastTime;
        if(time < 500){
            flag = true;
        }

        slastTime = System.currentTimeMillis();
        return flag;
    }


    @Override
    public boolean performClick() {
        if(onDoubleCkick()){
            return false;
        }

        toggle();

        final boolean handled = super.performClick();
        if(!handled){
            playSoundEffect(SoundEffectConstants.CLICK);
        }
        return handled;
    }

    private void toggle(){
        setChecked(!mChecked);
    }

    public void setChecked(boolean checked){
        if(mChecked != checked){
            mChecked = checked;
            refreshDrawableState();
        }
    }






    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAttachedToWindow = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAttachedToWindow = false;
    }

    @Override
    protected int getSuggestedMinimumWidth() {

        final float density = getContext().getResources().getDisplayMetrics().density;


        return (int)(DEFAULT_SIZE*density+0.5);
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return getSuggestedMinimumWidth();
    }

    private static int resolveSizeAndState(int desireSize, int measureSpec){
        int result = desireSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode){
            case MeasureSpec.UNSPECIFIED:
                result = desireSize;
                break;
            case MeasureSpec.AT_MOST:
                if(specSize < desireSize){
                    result = specSize;
                }else{
                    result = desireSize;
                }
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
            default:
                break;

        }
        return result;
    }

    private void initPaint(){
        mBroadPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBroadPaint.setStyle(Paint.Style.STROKE);
        mBroadPaint.setStrokeWidth(mBoardWidth);
        mBroadPaint.setColor(DEFAULT_BOARD_COLOR);
        mBroadPaint.setDither(true);

        mCoverPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCoverPaint.setStyle(Paint.Style.FILL);
        mCoverPaint.setColor(Color.BLUE);
        mCoverPaint.setDither(true);


        mRevealBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRevealBgPaint.setFilterBitmap(true);
        mRevealBgPaint.setStyle(Paint.Style.FILL);

    }

    private void initProprity(Context context,AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.SwitchButton);
        int count = a.getIndexCount();
        for(int i = 0;i< count;i++){
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.SwitchButton_boardWidth:
                    mBoardWidth = a.getInt(attr,DEFAUT_BOAED_WIDTH);
                    break;
                case R.styleable.SwitchButton_checkRevealColor:
                    mCheckRevealColor = a.getColor(attr,DEFAULT_CHECK_REVEAL_COLOR);
                    break;
                case R.styleable.SwitchButton_checkSrc:
                    int checkRes = a.getResourceId(attr,DEFAULT_CHECKED_DRAWABLE_ID);
                    mCheckedDrawable = context.getResources().getDrawable(checkRes);
                    break;
                case R.styleable.SwitchButton_unCheckRevealColor:
                    mUnCheckRevealColor = a.getColor(attr,DEFAULT_UNCHECK_REVEAL_COLOR);
                    break;
                case R.styleable.SwitchButton_uncheckSrc:
                    int unCheckRes = a.getResourceId(attr,DEFAULT_UNCHECKED_DRAWABLE_ID);
                    mUncheckDrawable = context.getResources().getDrawable(unCheckRes);
                    break;
                default:
                    break;

            }
        }
        a.recycle();
    }



}
