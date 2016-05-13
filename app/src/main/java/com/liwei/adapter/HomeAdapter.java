package com.liwei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.weili.practice.R;
import java.util.List;

/**
 * Created by wei.li on 2015/10/21.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<String> mDatas;
    private LayoutInflater mInflater;


    public HomeAdapter(Context context,List<String> datas){
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    public interface onItemClickListener{
             void onItemClick(View view,int position);
        }

        public  onItemClickListener listener;

        public void setonItemClickListener(onItemClickListener listener){
            this.listener = listener;
        }

    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater
                .inflate(R.layout.item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
      final  MyViewHolder holder1 = (MyViewHolder)holder;
                holder1.tv.setText(mDatas.get(position));
            if(listener != null){
                holder1.tv.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        int pos = holder1.getLayoutPosition();
                        listener.onItemClick(holder1.itemView,pos);
                    }
                });
            }
    }

//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//            holder.tv.setText(mDatas.get(position));
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
//    }

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
