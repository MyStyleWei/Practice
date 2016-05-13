package com.utils;

import android.util.AndroidException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Created by wei.li on 2016/4/8.
 */
public class RxUtil {

    private static final String TAG="Rxjava";
    private static List<Integer> list = new ArrayList<>();
    static {
        list.add(1);
        list.add(10);
        list.add(88);
        list.add(1000);
        list.add(10000);
    }



    public static void groupBy(){
        Observable.from(list).groupBy(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                if(integer >55){
                    return integer+"==";
                }
                return null;
            }
        }).subscribe(new Subscriber<GroupedObservable<String, Integer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GroupedObservable<String, Integer> stringIntegerGroupedObservable) {

                Log.i(TAG,stringIntegerGroupedObservable.getKey()+"===");
            }
        });
    }

    public static void flatMap(){

        Observable.from(list).flatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(final Integer integer) {
                return Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(integer+"===");
                        subscriber.onCompleted();
                    }
                });

            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted==");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG,s);
            }
        });

    }

    public static void buffer(){
         Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {

            }
        }).buffer(2).subscribe(new Subscriber<List<Integer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Integer> integers) {

            }
        });
    }

    public static void start(){

    }

    public static void interval(){
        final Observable observable = Observable.interval(3, TimeUnit.SECONDS);
        observable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
               System.out.println("interval==="+o.toString());
                if(Integer.parseInt(o.toString()) == 10){
                    this.unsubscribe();
                }
            }
        });
    }

    public void from(){
        Integer[] integers = {1,2,3,4,5,6};
        Observable<Integer> observable = Observable.from(integers);

        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

            }
        });
    }


    public void empty(){
        Observable.empty().subscribe(new Subscriber<Object>() {

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onNext(Object o) {

            }
        });
        Observable.never();
        Observable.error(new AndroidException());
    }

    public void defer(){
         Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                return null;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

            }
        });
    }

    public void create(){
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if(!subscriber.isUnsubscribed()){
                    for(int i = 0;i< 5;i++){
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                }
            }
        });

        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e(TAG,"onCompleted==");
            }

            @Override
            public void onError(Throwable e) {
               Log.e(TAG,"onError==");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG ,"onNext===>"+integer);
            }
        });

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {

            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

            }
        });
    }
}
