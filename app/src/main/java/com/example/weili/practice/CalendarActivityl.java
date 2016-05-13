package com.example.weili.practice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.widget.caladen.MonthAdapter;
import com.widget.caladen.MonthView;

import java.util.Calendar;
import java.util.Date;

public class CalendarActivityl extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_activityl);

        MonthView monthView = (MonthView) findViewById(R.id.monthView1);
        Date date = new Date();
        monthView.setTime(date.getTime());
        monthView.setAdapter(new MonthAdapter() {
            @Override
            public View createCellView(ViewGroup viewGroup, int position) {
                TextView textView  = new TextView(CalendarActivityl.this);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
            @Override
            public void bindCellView(ViewGroup viewGroup, View child, int position, Calendar calendar) {
                TextView textView  = (TextView) child;
                textView.setText(""+calendar.get(Calendar.DAY_OF_MONTH));
            }
        });
    }
}
