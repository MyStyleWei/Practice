package com.example.weili.practice;

import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fragments.DraFragment;
import com.fragments.DrawerFragment;
import com.fragments.SplashFragment;
import com.java.test.F;

public class Drawactivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private LinearLayout layout;
    private FrameLayout frameLayout;
    private DraFragment drawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawactivity);
        getDensity();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        layout = (LinearLayout) findViewById(R.id.tt) ;
        frameLayout = (FrameLayout)findViewById(R.id.conetnt);


        drawerFragment = new DraFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.conetnt,drawerFragment);
        transaction.commit();


        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                layout.getLocationOnScreen(location);
                Toast.makeText(Drawactivity.this,"===>>"+location[0]+"=="+location[1],Toast.LENGTH_LONG).show();

            }
        });

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.button_menu_normal);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
                int[] location = new int[2];
                v.getLocationOnScreen(location);
                Toast.makeText(Drawactivity.this,"===>>"+location[0]+"=="+location[1],Toast.LENGTH_LONG).show();

            }
        });


    }


    private void getDensity(){
          Toast.makeText(this,this.getResources().getDisplayMetrics().density+"...",Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return true;
    }
}
