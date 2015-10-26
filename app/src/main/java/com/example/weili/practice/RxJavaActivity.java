package com.example.weili.practice;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liwei.entity.Person;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;


public class RxJavaActivity extends ActionBarActivity {

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

            }

            @Override
            public void onError(Throwable e) {

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


        Observable.just("vjnri bn").subscribe(new Action1<String>() {
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

    }


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
