package com.liwei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wei.li on 2016/4/26.
 */
public  abstract  class RecyViewBaseAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<T> mDatas;
    private int mLayoutId;
    private OnRecycleViewItemClickListener mItemClickListener;


    public RecyViewBaseAdapter(Context context, List<T> datas){
        this.mContext = context;
        this.mDatas = datas;
    }

    public RecyViewBaseAdapter(Context context,List<T> datas,int layoutId){

        this.mContext = context;
        this.mDatas = datas;
        this.mLayoutId = layoutId;

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(mContext,getItemView(mLayoutId,parent));
        return holder;
    }

    protected View getItemView(int layoutId,ViewGroup parent){
        return LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       convert(holder,mDatas.get(position));
    }



    @Override
    public int getItemCount() {
        return 0;
    }

    protected abstract void convert( ViewHolder helper, T item);


    public void setItemClickListener(OnRecycleViewItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface OnRecycleViewItemClickListener{
        void onItemClick(View view,int position);
    }
}
