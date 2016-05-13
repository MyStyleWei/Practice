package com.example.weili.practice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liwei.adapter.RecyViewBaseAdapter;
import com.liwei.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ABAdapterRecycyViewActivity extends AppCompatActivity {

    private RecyclerView mView;
    private List<String>  mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abadapter_recycy_view);

        initDatas();
        mView = (RecyclerView) findViewById(R.id.recycle);
        mView.setLayoutManager(new LinearLayoutManager(this));
        mView.setAdapter(new RecyViewBaseAdapter(this,mList) {
            @Override
            protected void convert(ViewHolder helper, Object item) {

            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        });
    }

    private void initDatas(){
        for (int i = 'A';i<'Z';i++){
            mList.add((char) i +"");
        }
    }
}
