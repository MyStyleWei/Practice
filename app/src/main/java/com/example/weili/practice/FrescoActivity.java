package com.example.weili.practice;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoActivity extends AppCompatActivity {

    private SimpleDraweeView draweeView,draweeView2,draweeView3,draweeView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);

        String url = "http://img0.imgtn.bdimg.com/it/u=2631277772,1378837924&fm=21&gp=0.jpg";
        Uri uri = Uri.parse(url);
        draweeView = (SimpleDraweeView) findViewById(R.id.fresco);
        draweeView.setImageURI(uri);
        draweeView2 = (SimpleDraweeView) findViewById(R.id.fresco2);
        draweeView2.setImageURI(uri);
        draweeView3 = (SimpleDraweeView) findViewById(R.id.fresco3);
        draweeView3.setImageURI(uri);
        draweeView4 = (SimpleDraweeView) findViewById(R.id.fresco4);
        draweeView4.setImageURI(uri);

        draweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FrescoActivity.this,MainActivity.class).putExtra(MainActivity.TAG_EXIT,true));
            }
        });
    }
}
