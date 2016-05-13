package com.liwei.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wei.li on 2016/4/26.
 */
public class RecyTrueAdapter<T> extends RecyViewBaseAdapter {

    private Context mContext;
    private List<T> mDatas;

    public RecyTrueAdapter(Context context, List<T> datas){
        super(context,datas);

        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void convert(ViewHolder helper, Object item) {

    }
}
