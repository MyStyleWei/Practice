package com.example.weili.practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.liwei.custom.MySlidingMenu;

public class MySlidingMenuActivity extends AppCompatActivity {

    private ViewGroup mContent,mMenu;
    private ImageView imageView;
    private MySlidingMenu slidingMenu;
    private ListView menu_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sliding_menu);

        initView();
    }

    private void initView(){
        slidingMenu = (MySlidingMenu) findViewById(R.id.slidingmenu);
        mContent = (ViewGroup) findViewById(R.id.conetnt);
        mMenu = (ViewGroup) findViewById(R.id.menu);
        imageView = (ImageView) findViewById(R.id.menu_toggle);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingMenu.toggleMenu();
            }
        });

    }
}
