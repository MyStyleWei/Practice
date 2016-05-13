package com.example.weili.practice;

import android.animation.ValueAnimator;
import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AnimatorActivity extends AppCompatActivity {

    private LinearLayout showView,goneView;
    private boolean isShow = false;
    private int height,desity;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

     initView();
    }

    void initView(){
        showView = (LinearLayout) findViewById(R.id.mainLayout);
        goneView = (LinearLayout) findViewById(R.id.goneView);
        showView.post(new Runnable() {
            @Override
            public void run() {
                desity = (int) getResources().getDisplayMetrics().density;
                height = (int)(desity*goneView.getMeasuredHeight()+0.5);
            }
        });

        imageView = (ImageView) findViewById(R.id.img2);
        ((Animatable)imageView.getDrawable()).start();



        showView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goneView();
            }
        });
        goneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isShow){
                    openView();
                }
            }
        });
    }


    void openView(){
        showView.setVisibility(View.VISIBLE);
        ValueAnimator animator = ValueAnimator.ofInt(0,height);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams params = showView.getLayoutParams();
                params.height = value;
                showView.setLayoutParams(params);
            }
        });
       System.out.println("1111111111111111");
        animator.start();
    }

    void goneView(){
        ValueAnimator animator = ValueAnimator.ofInt(height,0);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams params = showView.getLayoutParams();
                params.height = value;
                showView.setLayoutParams(params);
            }
        });
        System.out.println("22222222222222");
        animator.start();
    }
}
