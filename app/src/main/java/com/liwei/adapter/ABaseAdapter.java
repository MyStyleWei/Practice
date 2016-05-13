package com.liwei.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by wei.li on 2016/4/12.
 */
public abstract class ABaseAdapter<T> extends BaseAdapter {


    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private int layoutId;


    public ABaseAdapter(Context context,int layoutId,List<T> datas){
        mContext = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(mContext);
        this.layoutId = layoutId;
    }


    @Override
    public int getCount() {
        return (mDatas == null || mDatas.size()<0)? 0:mDatas.size();
    }


    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext,convertView,parent,
                layoutId,position);
        convert(holder,getItem(position));
        return holder.getConvertView();
    }


    public abstract void convert(ViewHolder holder,T T);
}
