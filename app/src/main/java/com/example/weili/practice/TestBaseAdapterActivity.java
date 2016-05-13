package com.example.weili.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liwei.adapter.ABaseAdapter;
import com.liwei.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestBaseAdapterActivity extends AppCompatActivity {

    private ListView mListView;
    private List<String> mList = new ArrayList<>(Arrays.asList("MultiItem ListView",
            "RecyclerView",
            "MultiItem RecyclerView","RecyclerViewWithHeader"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_base_adapter);

        mListView = (ListView) findViewById(R.id.lv);
        mListView.setAdapter(new ABaseAdapter<String>(this,R.layout.item,mList) {
            @Override
            public void convert(ViewHolder holder, String T) {
                holder.setText(R.id.id_num,T);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class<?> clazz = null;
                if(clazz == null){
                    switch (position){
                        case 0:
                            break;
                        case 1:
                            clazz = ABAdapterRecycyViewActivity.class;
                            break;
                        default:
                            break;
                    }
                }

                Intent intent = new Intent(TestBaseAdapterActivity.this,clazz);
                startActivity(intent);
            }
        });
    }
}
