package com.example.weili.practice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import com.fragments.TransitionFragment;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;

public class TestCenterActivity extends AppCompatActivity {

    private TransitionManager mTransitionManager;
    private Scene mScene1;
    private Scene mScene2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_center);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new TransitionFragment())
                    .commit();
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new TransitionFragment())
//                    .commit();
        }
        ViewGroup container = (ViewGroup)findViewById(R.id.container);
        TransitionInflater transitionInflater = TransitionInflater.from(this);
        mTransitionManager = transitionInflater.inflateTransitionManager(R.transition.transition_manager, container);
        mScene1 = Scene.getSceneForLayout(container, R.layout.fragment_transition_scene_1, this);
        mScene2 = Scene.getSceneForLayout(container, R.layout.fragment_transition_scene_2, this);
    }

    public void goToScene1(View view) {
        mTransitionManager.transitionTo(mScene1);
    }

    public void goToScene2(View view) {
        mTransitionManager.transitionTo(mScene2);
    }


    private void showPopup() {
//        AnimatorSet set = new AnimatorSet();
//        set.playTogether(
//                ObjectAnimator.ofFloat(mPopupHotel, "scaleX", 0, 1f),
//                ObjectAnimator.ofFloat(mPopupHotel, "scaleY", 0, 1f),
//                ObjectAnimator.ofFloat(mPopupHotel, "alpha", 0, 1f)
//        );
//        set.setDuration(300).start();
    }

    /**
     * 隐藏弹层
     */
    private void closePopup() {
        AnimatorSet set = new AnimatorSet();
//        set.playTogether(
//                ObjectAnimator.ofFloat(mPopupHotel, "scaleX", 1f, 0),
//                ObjectAnimator.ofFloat(mPopupHotel, "scaleY", 1f, 0),
//                ObjectAnimator.ofFloat(mPopupHotel, "alpha", 1f, 0)
//        );
        set.setDuration(300).start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
//                defaultFinish();
//                mActivity.overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }
}
