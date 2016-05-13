package com.example.weili.practice;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liwei.custom.WindowView;
import com.utils.NdkJniUtils;

import java.util.Timer;

public class NDActivity extends AppCompatActivity {

    private TextView mText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nd);

     //   initView();
        test();
    }

    private void initView(){
        mText = (TextView) findViewById(R.id.text);

        NdkJniUtils utils = new NdkJniUtils();

        mText.setText(utils.getCLanguageString());
        int scaledTouchSlop = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
    }


    private void test(){
        Button button = new Button(this);
        button.setText("wfniwe");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NDActivity.this,"111",Toast.LENGTH_SHORT).show();
            }
        });
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.format = PixelFormat.RGBA_8888;
        params.flags = WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN;
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        params.gravity = Gravity.RIGHT|Gravity.BOTTOM;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.x = 100;
        params.y = 300;
        manager.addView(button,params);

    }
}
