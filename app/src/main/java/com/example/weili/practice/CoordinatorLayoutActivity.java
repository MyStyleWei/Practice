package com.example.weili.practice;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.liwei.custom.MyPop;

public class CoordinatorLayoutActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        initToorBar();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_content);
    //     initPop();
    }

    void initPop(){
        MyPop myPop = new MyPop(this);
        myPop.show(coordinatorLayout,this);
    }

   void  initToorBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
    }
}
