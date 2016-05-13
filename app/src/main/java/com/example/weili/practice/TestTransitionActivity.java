package com.example.weili.practice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.RelativeLayout;

import com.widget.caladen.RevealBackgroundView;

public class TestTransitionActivity extends AppCompatActivity {


    RelativeLayout mLayout;
    RevealBackgroundView mBackgroundView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        int flag = getIntent().getIntExtra("flag",0);


        switch (flag){
            case 0:
                getWindow().setEnterTransition(new Explode());
                break;
            case 1:
                getWindow().setEnterTransition(new Slide());
                break;

            case 2:
                getWindow().setEnterTransition(new Fade());
                getWindow().setExitTransition(new Fade());
                break;
            case 3:
                break;
        }
        setContentView(R.layout.activity_test_transition);

        initReve();


        mLayout = (RelativeLayout) findViewById(R.id.container);

//        int[] locationY = getIntent().getIntArrayExtra("location");
//        if(locationY[1] != 0){
//           mLayout.setScaleY(0.1f);
//            mLayout.setPivotY(locationY[1]);
//
//            mLayout.animate()
//                    .scaleY(1)
//                    .setDuration(200)
//                    .setInterpolator(new AccelerateDecelerateInterpolator())
//                    .start();
//        }
    }

    private void initReve(){
        final int[] location = getIntent().getIntArrayExtra("location");
        mBackgroundView = (RevealBackgroundView) findViewById(R.id.bgView);
        mBackgroundView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mBackgroundView.getViewTreeObserver().removeOnPreDrawListener(this);
                mBackgroundView.startFromLocation(location);
                return true;
            }
        });

    }
}
