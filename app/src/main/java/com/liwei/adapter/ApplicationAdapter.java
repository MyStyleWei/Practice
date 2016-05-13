package com.liwei.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.weili.practice.R;
import com.liwei.entity.AppInfo;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by wei.li on 2016/2/24.
 */
public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ViewHolder> {

    private List<AppInfo> appInfos;
    private int layoutId;

    public ApplicationAdapter(List<AppInfo> appInfos,int layoutId){
        this.appInfos = appInfos;
        this.layoutId = layoutId;
    }


    public void addAppliactions(List<AppInfo> appInfos){
        this.appInfos.clear();
        this.appInfos.addAll(appInfos);
        notifyDataSetChanged();

    }


    public void addAppliaction(int position,AppInfo appInfo){

        if(position < 0){
            position = 0;
        }

        appInfos.add(position,appInfo);
        notifyItemInserted(position);
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        AppInfo appInfo = appInfos.get(position);
        holder.name.setText(appInfo.getmName());
        getBitmap(appInfo.getmIcon())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        holder.image.setImageBitmap(bitmap);
                    }
                });


    }


    private Observable<Bitmap> getBitmap( final String icon){
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                subscriber.onNext(BitmapFactory.decodeFile(icon));
                subscriber.onCompleted();
            }
        });
    }





    @Override
    public int getItemCount() {
        return appInfos == null ? 0:appInfos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public ImageView image;

        public ViewHolder(View itemView){
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.image);
        }

    }

}
