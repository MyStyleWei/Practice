package com.example.weili.practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.liwei.custom.RangeSeekbar;

public class RangSeekBarActivity extends AppCompatActivity {


    private TextView fromTime,endTime;
    private RangeSeekbar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rang_seek_bar);

        initView();
    }


    void initView(){
            fromTime = (TextView) findViewById(R.id.fromTime);
            endTime = (TextView) findViewById(R.id.endTime);
            seekbar = (RangeSeekbar) findViewById(R.id.seekbar);
            seekbar.setOnCursorChangeListener(new RangeSeekbar.OnCursorChangeListener() {
                @Override
                public void onLeftCursorChanged(int location, String textMark) {
                    fromTime.setText(textMark+"");
                }

                @Override
                public void onRightCursorChanged(int location, String textMark) {
                    endTime.setText(textMark+"");
                }
            });

    }
}
