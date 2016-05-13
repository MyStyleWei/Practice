package com.example.weili.practice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liwei.entity.Person;
import com.utils.RxUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;


public class RxJavaActivity extends Activity {

    private TextView text;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        text = (TextView) findViewById(R.id.text2);
        img = (ImageView) findViewById(R.id.reimg);


        List<Integer> list = new ArrayList<>();
        for(int i= 1;i< 10;i++){
            list.add(i);
        }

        Observable.from(list).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return " eklrj "+integer;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted!");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError!");
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(getApplicationContext(), "44444", Toast.LENGTH_SHORT).show();
                System.out.println(s);
            }
        });


        Observable<Person> observable = Observable.create(new Observable.OnSubscribe<Person>() {
            @Override
            public void call(Subscriber<? super Person> subscriber) {
                subscriber.onNext(new Person(1,"iwei","ieruvgb","18253591920","wjhvgbgje"));
                subscriber.onCompleted();
            }
        });


        Observable.just("vjnribn").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });



        Subscriber<Person> mysub = new Subscriber<Person>() {
            @Override
            public void onCompleted() {

                Toast.makeText(getApplicationContext(),"2222",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

                Toast.makeText(getApplicationContext(),"3333",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Person person) {

                Toast.makeText(getApplicationContext(),"11111",Toast.LENGTH_SHORT).show();
                text.setText(person.toString());
            }
        };

        observable.subscribe(mysub);
        observable.lift(new Observable.Operator<String, Person>() {
            @Override
            public Subscriber<? super Person> call(Subscriber<? super String> subscriber) {
                return null;
            }
        });

        RxUtil.groupBy();

    }

    private void single(){
      Scheduler.Worker worker =  Schedulers.newThread().createWorker();
        worker.schedule(new Action0() {
            @Override
            public void call() {

            }
        });
    }

    private void practice(){
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                 subscriber.onNext(33);
                subscriber.onCompleted();

            }
        });
    }


    private void buffer(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(10);
        list.add(88);
        list.add(1000);
        list.add(10000);


        Observable.from(list)
                .buffer(2)
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        if(integers.size() <2){
                            Log.e("Rx_Java_buffer==",""+integers.get(0));
                        }else{
                            Log.e("Rx_Java_buffer==",""+integers.get(0)+"///"+integers.get(1));
                        }

                    }
                });

        Observable.from(list)
                .buffer(2 ,2)
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        if(integers.size() <2){
                            Log.e("Rx_Java_buffer==",""+integers.get(0));
                        }else{
                            Log.e("Rx_Java_buffer==",""+integers.get(0)+"///"+integers.get(1));
                        }
                    }
                });
    }


    /**
     * 修改Observable
     */
    private void map(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(10);
        list.add(88);
        list.add(1000);
        list.add(10000);

        Observable.from(list)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        return integer+integer2;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.e("Rx_Java_scan1==",""+integer);
            }
        });


        Observable.from(list)
                .scan(2, new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        return  integer+integer2;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.e("Rx_Java_scan",""+integer);
            }
        });


//        Observable.from(list)
//                .concatMap(new Func1<Integer, Observable<Integer>>() {
//                    @Override
//                    public Observable<Integer> call(final Integer integer) {
//                        return Observable.create(new Observable.OnSubscribe<Integer>() {
//                            @Override
//                            public void call(Subscriber<? super Integer> subscriber) {
//                                subscriber.onNext(integer +3);
//                                subscriber.onCompleted();
//                            }
//                        });
//                    }
//                }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.e("Rx_Java_concat",""+integer);
//            }
//        });
//
//
//
//        Observable.from(list)
//                .flatMap(new Func1<Integer, Observable<Integer>>() {
//                    @Override
//                    public Observable<Integer> call(final Integer integer) {
//                        return Observable.create(new Observable.OnSubscribe<Integer>() {
//                            @Override
//                            public void call(Subscriber<? super Integer> subscriber) {
//                                subscriber.onNext(integer +2);
//                                subscriber.onCompleted();
//                            }
//                        });
//                    }
//                }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.e("Rx_Java_map",""+integer);
//            }
//        });



//
//        Observable.from(list)
//                .map(new Func1<Integer, Boolean>() {
//                    @Override
//                    public Boolean call(Integer integer) {
//                        return integer > 101;
//                    }
//                }).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                Log.e("Rx_Java_map",""+aBoolean);
//            }
//        });

//         Observable.from(list)
//                .map(new Func1<Integer, Integer>() {
//                    @Override
//                    public Integer call(Integer integer) {
//                        return integer+2;
//                    }
//                }).subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.e("Rx_Java_map",""+integer);
//                    }
//                });
    }


    /**
     * 将数组转换为list
     */
    private void array(){
        String[] str = {"11","222","3333","4444"};
        List<String> list = Arrays.asList(str);
        for(String s : list){
            Log.e("RxJava_array",String.valueOf(list));
        }
    }


    private  void timer(){
        Observable.timer(3,3,TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.e("RxJava_timer",String.valueOf(aLong));
                    }
                });
    }

    /**
     * interval(interval, time)
     * 用于周期的任务
     */
    private void interval(){
        Observable.interval(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                       Log.e("RxJava_interval",String.valueOf(aLong));

                    }
                });
    }




    private void range(){
        Observable.range(10,10)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        if(integer >=14){
                            Log.e("RxJava_range",String.valueOf(integer));

                            return;
                        }

                    }
                });
    }



    private void defer(){
       Observable<Integer> observable = Observable.defer(new Func0<Observable<Integer>>() {
           @Override
           public Observable<Integer> call() {
               return getInt();
           }
       });

        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("RxJava_defer"+String.valueOf(integer));
            }
        });

    }

    private  Observable<Integer> getInt(){
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Log.e("RxJava_defer","GETINT");
                subscriber.onNext(42);
                subscriber.onCompleted();
            }
        });
    }



    private void asyncSubject(){
        AsyncSubject<Integer> subject = AsyncSubject.create();
        subject.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("AsyncSubject==>>"+integer);
            }
        });

        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.onCompleted();
    }



    private void repLlaySubject(){
        ReplaySubject<Integer> subject = ReplaySubject.create();
         subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.onCompleted();

        subject.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("ReplaySubject==>>"+integer);
            }
        });

        subject.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("ReplaySubject2222==>>"+integer);
            }
        });
    }


    private void behaviorSubject(){
        /**
         * 获取历史最新的消息和以后的消息
         * 1。若在onCompleted 之后订阅  则收到onCompleted消息 同OnError
         */
        BehaviorSubject<Integer> subject = BehaviorSubject.create(0);
        subject.onNext(4);
        subject.onNext(5);
        subject.onNext(6);
        subject.onNext(7);

        subject.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("BehaviorSubject==>>"+integer);
            }
        });
        subject.onNext(1);

        subject.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("BehaviorSubject2222==>>"+integer);
            }
        });

        subject.onNext(2);
        subject.onCompleted();

    }



    private  void publishSubject(){
//        PublishSubject<String> stringPublishSubject = PublishSubject.create();
//        Subscription subscription = stringPublishSubject.subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//              Log.e("Rx_PublishSubject",s);
//            }
//        });
//        stringPublishSubject.onNext("Hello World");
//
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e("Rx_PublishSubject==>>",s);
//            }
//        };
//        stringPublishSubject.subscribe(observer);
//        stringPublishSubject.onNext("ttttttt");
//        stringPublishSubject.onCompleted();

        final PublishSubject<Boolean> subject = PublishSubject.create();
        subject.subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                System.out.println("subject Completed!");
            }
        });


        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i = 0;i<5;i++){
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).doOnCompleted(new Action0() {
            @Override
            public void call() {
                subject.onNext(true);
            }
        }).subscribe();

    }


    private void  just(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(10);
        list.add(100);
        list.add(1000);
        list.add(10000);

        Observable.empty();

        Observable<List<Integer>> observable = Observable.just(list);
        Subscription subscription = observable.subscribe(new Observer<List<Integer>>() {
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


        String[] str = {"1","2"};
        Observable< String[]> observable2 = Observable.just(str);
        Subscription subscription1 = observable2.subscribe(new Observer<String[]>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String[] strings) {

            }
        });


    }

    private void in(){

    }


    private int getIndex(int a,int b){
        return a+b;
    }


    private void from(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(10);
        list.add(100);
        list.add(1000);
        list.add(10000);

        Observable<Integer> observable = Observable.from(list);
        Subscription subscription = observable.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.e("Rx-FROM",integer+"");
            }
        });


    }

    public void lift(){




        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i = 0;i<5;i++){
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        });

        Subscription subscription2 = observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("Item is==>"+ integer);
            }
        });

//        Subscription subscription = observable.subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                System.out.println("Item is==>"+ integer);
//            }
//        });


//        Subscription subscription = observable.subscribe(new Observer<Integer>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("lift==>onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("lift==>onError");
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                System.out.println("Item is==>"+ integer);
//            }
//        });


    };


    private void initObservable(){
        //创建 Observable
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!qwf ");
                        sub.onCompleted();
                    }
                }
        );

        //创建 Subscriber
        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
  //              System.out.println(s);
                text.setText(s);
            }

            // 如果正确的终结，最后会调到这里
            @Override
            public void onCompleted() {

            }

            // 只要有异常抛出（包括操作符中的调用），会调到这里
            @Override
            public void onError(Throwable e) { }
        };

        //mySubscriber订阅myObservable
        myObservable.subscribe(mySubscriber);
    }




    private void i(){
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("ewfer");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        observable.subscribe(subscriber);

        //单纯的发送事件
        Observable<String> observable1 = Observable.just("WEfew");
        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                //处理接收到的事件

            }
        };

        observable1.subscribe(action1);

        Observable.just("wegeuoirgh").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });


        //加入OperaTORS
       Subscription subscription =  Observable.just("#Basic Markdown toHTML").map(new Func1<String, Object>() {
            @Override
            public Object call(String s) {
                if(s != null && s.startsWith("#")){
                    return "<h1>"+s.substring(1,s.length())+"</h1>";
                }
                return null;
            }
        }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {

            }
        });
        subscription.unsubscribe();
        subscription.isUnsubscribed();

    }

    private void cre(){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        });

        Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {

            }
        }).subscribe(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> strings) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rx_java, menu);
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
