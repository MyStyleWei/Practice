package com.example.weili.practice;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liwei.custom.PanelPieChartLabel;
import com.liwei.custom.PieView;
import com.liwei.entity.PieData;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class RoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<PieData> datas = new ArrayList<>();
        datas.add(new PieData("111",10f));
        datas.add(new PieData("222",20f));
        datas.add(new PieData("333",30f));
        datas.add(new PieData("444",15f));
        datas.add(new PieData("555",25f));
        PieView pieView = new PieView(this);
        pieView.setmStartAngle(0f);
        pieView.setmData(datas);
        this.setTitle("给饼图标上百分比");
        setContentView(new PanelPieChartLabel(this));
    }


    private MyHandler myHandler = new MyHandler(this);

    private static class MyHandler extends Handler{

        private WeakReference<Context> reference;

        public MyHandler(Context context){
            reference = new WeakReference<Context>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RoundActivity activity = (RoundActivity) reference.get();
            if(activity != null){
             //   activity.moveTaskToBack()
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
    }
}
