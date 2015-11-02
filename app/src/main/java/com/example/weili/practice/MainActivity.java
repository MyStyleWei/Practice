package com.example.weili.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.liwei.adapter.HomeAdapter;
import com.liwei.custom.DividerItemDecoration;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;
    private String[] str = {"SnackbarActivity","BarrageActivity","CavaActivity"
            ,"ScorActivity","RxJavaActivity","LoginActivity","RadialActivity"
            ,"SweepGradientActivity","RefWatcherActivity","FragmentActivity"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter(this,mDatas));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mAdapter.setonItemClickListener(new HomeAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Class targetClass = null;
                if(targetClass == null){
                    switch (position){
                        case 0:
                            targetClass = SnackbarActivity.class;
                            break;
                        case 1:
                            targetClass = BarrageActivity.class;
                            break;
                        case 2:
                            targetClass = CavaActivity.class;
                            break;
                        case 3:
                            targetClass = ScorActivity.class;
                            break;
                        case 4:
                            targetClass = RxJavaActivity.class;
                            break;
                        case 5:
                            targetClass = LoginActivity.class;
                            break;
                        case 6:
                            targetClass = RadialActivity.class;
                            break;
                        case 7:
                            targetClass = SweepGradientActivity.class;
                            break;
                        case 8:
                            targetClass = RefWatcherActivity.class;
                            break;
                        case 9:
                            targetClass = FragmentActivity.class;
                            break;
                        default:
                            break;
                    }

                    Intent intent = new Intent(MainActivity.this,targetClass);
                    startActivity(intent);
                }

            }
        });

    }

    private void initDatas(){
        mDatas = new ArrayList<String>();
        for(int i = 0;i<str.length;i++){
            mDatas.add(str[i]);
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
