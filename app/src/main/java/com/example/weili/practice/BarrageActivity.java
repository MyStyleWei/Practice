package com.example.weili.practice;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.liwei.custom.BarrageView;

import java.util.Random;



public class BarrageActivity extends ActionBarActivity {

    public static final int DELAY_TIME = 800;
    private Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barrage);
        final String[] texts = getResources().getStringArray(R.array.default_text_array);
        //设置宽高全屏
        final ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        final Handler handler = new Handler(){

        };
        Runnable createBarrageView = new Runnable() {
            @Override
            public void run() {
                //新建一条弹幕，并设置文字
                final BarrageView barrageView = new BarrageView(BarrageActivity.this);
                barrageView.setText(texts[random.nextInt(texts.length)]); //随机设置文字
                addContentView(barrageView, lp);

                //发送下一条消息
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(createBarrageView);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_barrage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
