package com.example.weili.practice;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
//        mAdapter.setonItemClickListener(new HomeAdapter.onItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(MainActivity.this,position,Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void initDatas(){
        mDatas = new ArrayList<String>();
        for(int i = 'A';i<'z';i++){
            mDatas.add(""+(char)i );
        }
    }

    public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{



//        public interface onItemClickListener{
//             void onItemClick(View view,int position);
//        }
//
//        public  onItemClickListener listener;
//
//        public void setonItemClickListener(onItemClickListener listener){
//            this.listener = listener;
//        }

        @Override
        public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(MainActivity.this)
                  .inflate(R.layout.item,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
              holder.tv.setText(mDatas.get(position));
//            if(listener != null){
//                holder.tv.setOnClickListener(new View.OnClickListener(){
//
//                    @Override
//                    public void onClick(View v) {
//                        int pos = holder.getLayoutPosition();
//                        listener.onItemClick(holder.itemView,pos);
//                    }
//                });
//            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            public MyViewHolder(View view){
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
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
