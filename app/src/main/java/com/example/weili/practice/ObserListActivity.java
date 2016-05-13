package com.example.weili.practice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.application.MyApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liwei.adapter.ApplicationAdapter;
import com.liwei.adapter.HomeAdapter;
import com.liwei.entity.AppInfo;
import com.liwei.entity.AppInfoRich;
import com.liwei.entity.ApplicationsList;
import com.utils.AppUtils;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;


public class ObserListActivity extends AppCompatActivity {



    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private ApplicationAdapter adapter;

    private File mFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obser_list);

        initView();
    }


    private void initView(){
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swip);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        adapter = new ApplicationAdapter(new ArrayList<AppInfo>(),R.layout.obser_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.myPrimaryColor));
        refreshLayout.setProgressViewOffset(false,0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,24,getResources().getDisplayMetrics()));

        refreshLayout.setEnabled(false);
        refreshLayout.setRefreshing(true);
        recyclerView.setVisibility(View.GONE);

        getFileDir().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<File>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(File file) {
                        mFile = file;
                        refreshTheList();
                    }
                });

    }


    private void refreshTheList(){
        getApps()
                .take(5)
//                .filter(new Func1<AppInfo, Boolean>() {
//                    @Override
//                    public Boolean call(AppInfo appInfo) {
//                        return appInfo.getmName().startsWith("C");
//                    }
//                })
             //   .repeat(3)
             //   .distinct()
             //   .first()
              //  .last()
                //  .skipLast(1)
                .elementAt(3)
//                .scan(new Func2<AppInfo, AppInfo, AppInfo>() {
//                    @Override
//                    public AppInfo call(AppInfo appInfo, AppInfo appInfo2) {
//                        return null;
//                    }
//                })
                .toSortedList()


                .subscribe(new Observer<List<AppInfo>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(ObserListActivity.this, "Here is the list!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ObserListActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        refreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(List<AppInfo> appInfos) {
                        recyclerView.setVisibility(View.VISIBLE);

                        Toast.makeText(ObserListActivity.this,appInfos.size()+"",Toast.LENGTH_SHORT).show();
                        adapter.addAppliactions(appInfos);

                        refreshLayout.setRefreshing(false);
                        storeList(appInfos);
                    }
                });
    }


    private Observable<File> getFileDir(){
        return Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                subscriber.onNext(MyApplication.context.getFilesDir());
                subscriber.onCompleted();

            }
        });
    }


    private void storeList(final List<AppInfo> appInfos){

        ApplicationsList.getInstance().setAppInfos(appInfos);
        Schedulers.io().createWorker().schedule(new Action0() {
            @Override
            public void call() {
                SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
                Type type = new TypeToken<List<AppInfo>>(){}.getType();
                preferences.edit().putString("APPS",new Gson().toJson(appInfos,type)).apply();
            }
        });
    }



    private Observable<AppInfo>  getApps(){
        return Observable.create(new Observable.OnSubscribe<AppInfo>() {
            @Override
            public void call(Subscriber<? super AppInfo> subscriber) {
                List<AppInfoRich> apps = new ArrayList<AppInfoRich>();
                final Intent intent = new Intent(Intent.ACTION_MAIN,null);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> infos = getPackageManager().queryIntentActivities(intent,0);
                for(ResolveInfo info : infos){
                    apps.add(new AppInfoRich(ObserListActivity.this,info));
                }

                for(AppInfoRich infoRich :apps){
                    Bitmap icon = AppUtils.drawableToBitmap(infoRich.getIcon());
                    String name = infoRich.getName();
                    String iconPath = mFile+"/"+name;
                    AppUtils.storeBitmap(ObserListActivity.this,icon,name);

                    if(subscriber.isUnsubscribed()){
                        return;
                    }

                    subscriber.onNext(new AppInfo(infoRich.getLastUpdateTime(),name,iconPath));
                }
                if(!subscriber.isUnsubscribed()){
                    subscriber.onCompleted();
                }
            }
        });
    }
}
