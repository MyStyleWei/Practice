package com.example.weili.practice;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.liwei.custom.WindowView;

public class CustomCanvasActivity extends AppCompatActivity {


    private ImageView imageView;
    private Button button;
    private boolean isshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_canvas);

        initView();
    }

    private void initView(){
        imageView = (ImageView) findViewById(R.id.img);
        button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(!isshow){
                  hideImageCircular();
              }else{
                  revealImageCircular();
              }

                isshow = !isshow;
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void hideImageCircular() {
        int x = imageView.getMeasuredWidth()/2;
        int y = imageView.getMeasuredHeight()/2;
        int radius = imageView.getMeasuredWidth();

        ValueAnimator anim =
                (ValueAnimator) ViewAnimationUtils.createCircularReveal(imageView, x, y, radius, 0);

        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageView.setVisibility( View.INVISIBLE );
            }
        });

        anim.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void revealImageCircular() {
        int x = imageView.getMeasuredWidth()/2;
        int y = imageView.getMeasuredHeight()/2;
        int radius = imageView.getMeasuredWidth();

        ValueAnimator anim =
                (ValueAnimator) ViewAnimationUtils.createCircularReveal(imageView, x, y, 0, radius);

        anim.setDuration( 1000 );
        anim.addListener( new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                imageView.setVisibility( View.VISIBLE );
            }
        });

        anim.start();
    }
}
