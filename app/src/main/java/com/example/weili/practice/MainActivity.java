package com.example.weili.practice;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fragments.SplashFragment;
import com.liwei.adapter.RecycAdapter;
import com.liwei.custom.DividerItemDecoration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity  {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private RecycAdapter mAdapter;
    private String[] str = {"SnackbarActivity","BarrageActivity","CavaActivity"
            ,"ScorActivity","RxJavaActivity","LoginActivity","RadialActivity"
            ,"SweepGradientActivity","RefWatcherActivity"
            ,"DialogActivity","MyDialogActivity","ObseverActivity","ScrollActivity"
            ,"RealmObihectActivity","CustomScrollerActivityt","TestGestActivity"
            ,"ViewGrouView","CaralmActivity","WindowViewActivity","ScrollToActivity"
            ,"RippleButtonActivity","FaceOverlayViewActivity","CardActivity","ListActivity"
            ,"RecycActivity","HeadViewActivity","ExpandListActivity","ExpandActivity"
            ,"PaintActivity","PopActivity","EditActivity","TestEditActivity","ToobarActivity"
    ,"SpanActivity","InputActivity","NativiewActivity","OrderActivity","RevealTextViewActivity"
    ,"PeriscopeActivity","FrescoActivity","CalendarActivityl","ExpandTextViewActivity","EdittextActivity"
    ,"CoordinatorLayoutActivity","Drawactivity","UnderLineActivity","ScrollerActivity","CustomCanvasActivity"
    ,"NDActivity","BlueTeethActivity","TDviewActivity","MySlidingMenuActivity","ObserListActivity","RoundActivity"
    ,"RangeSeekbar","WebviewActivity","AnimatorActivity","TsetSVGAnimatorActivity","TestBaseAdapterActivity"
    ,"TestCenterActivity","TestOptionsActivity","ActivityOptionsActivity","TypeListViewActivity","PaintXfermodeActivity "};
    private static boolean isshow = false;
    private Toolbar toolbar;
    private LinearLayout layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectAll()
//                .penaltyLog()
//                .penaltyDialog()
//                .build());
//
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                .detectAll()
//                .penaltyLog()
//                .build());


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.button_menu_normal);

        Toast.makeText(this,MainActivity.class.getCanonicalName(),Toast.LENGTH_SHORT).show();
       // initSplashFragment();

     //   test();
//        getWindow().getDecorView().post(new Runnable() {
//            @Override
//            public void run() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                },3000);
//
//            }
//        });
        layout = (LinearLayout) findViewById(R.id.lin);

        initDatas();


        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //       mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        //      mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(6, StaggeredGridLayoutManager.HORIZONTAL));
        mRecyclerView.setAdapter(mAdapter = new RecycAdapter(this, mDatas));
        //     mRecyclerView.addItemDecoration(new DividerGridDecoration(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListener(new RecycAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Class targetClass = null;
                if (targetClass == null) {
                    switch (position) {
                        case 0:
                            targetClass = SnackbarActivity.class;
                            break;
                        case 1:
                            targetClass = BarrageActivity.class;
                            break;
                        case 2:
                            targetClass = CavaActivity.class;
                            break;
                        case 3:
                            targetClass = ScorActivity.class;
                            break;
                        case 4:
                            targetClass = RxJavaActivity.class;
                            break;
                        case 5:
                            targetClass = LoginActivity.class;
                            break;
                        case 6:
                            targetClass = RadialActivity.class;
                            break;
                        case 7:
                            targetClass = SweepGradientActivity.class;
                            break;
                        case 8:
                            targetClass = RefWatcherActivity.class;
                            break;

                        case 9:
                            targetClass = DialogActivity.class;
                            break;
                        case 10:
                            targetClass = MyDialogActivity.class;
                            break;
                        case 11:
                            targetClass = ObseverActivity.class;
                            break;
                        case 12:
                            targetClass = ScrollActivity.class;
                            break;
                        case 13:
                            targetClass = RealmObihectActivity.class;
                            break;
                        case 14:
                            targetClass = CustomScrollerActivityt.class;
                            break;
                        case 15:
                            targetClass = TestGestActivity.class;
                            break;
                        case 16:
                            targetClass = ViewGrouActivity.class;
                            break;
                        case 17:
                            targetClass = CaralmActivity.class;
                            break;
                        case 18:
                            targetClass = WindowViewActivity.class;
                            break;
                        case 19:
                            targetClass = ScrollToActivity.class;
                            break;
                        case 20:
                            targetClass = RippleButtonActivity.class;
                            break;
                        case 21:
                            targetClass = FaceOverlayViewActivity.class;
                            break;
                        case 22:
                            targetClass = CardActivity.class;
                            break;
                        case 23:
                            targetClass = ListActivity.class;
                            break;
                        case 24:
                            targetClass = RecycActivity.class;
                            break;
                        case 25:
                            targetClass = HeadViewActivity.class;
                            break;
                        case 26:
                            targetClass = ExpandListActivity.class;
                            break;
                        case 27:
                            targetClass = ExpandActivity.class;
                            break;
                        case 28:
                            targetClass = PaintActivity.class;
                            break;
                        case 29:
                            targetClass = PopActivity.class;
                            break;
                        case 30:
                            targetClass = EditActivity.class;
                            break;
                        case 31:
                            targetClass = TestEditActivity.class;
                            break;
                        case 32:
                            targetClass = ToobarActivity.class;
                            break;
                        case 33:
                            targetClass = SpanActivity.class;
                            break;
                        case 34:
                            targetClass = InputActivity.class;
                            break;
                        case 35:
                            targetClass = NativiewActivity.class;
                            break;
                        case 36:
                            targetClass = OrderActivity.class;
                            break;
                        case 37:
                            targetClass = RevealTextViewActivity.class;
                            break;
                        case 38:
                            targetClass = PeriscopeActivity.class;
                            break;
                        case 39:
                            targetClass = FrescoActivity.class;
                            break;
                        case 40:
                            targetClass = CalendarActivityl.class;
                            break;
                        case 41:
                            targetClass = ExpandTextViewActivity.class;
                            break;
                        case 42:
                            targetClass = EdittextActivity.class;
                            break;
                        case 43:
                            targetClass = CoordinatorLayoutActivity.class;
                            break;
                        case 44:
                            targetClass = Drawactivity.class;
                            break;
                        case 45:
                            targetClass = UnderLineActivity.class;
                            break;
                        case 46:
                            targetClass = ScrollerActivity.class;
                            break;
                        case 47:
                            targetClass = CustomCanvasActivity.class;
                            break;
                        case 48:
                            targetClass = NDActivity.class;
                            break;
                        case 49:
                            targetClass = BlueTeethActivity.class;
                            break;
                        case 50:
                            targetClass = TDviewActivity.class;
                            break;
                        case 51:
                            targetClass = MySlidingMenuActivity.class;
                            break;
                        case 52:
                            targetClass = ObserListActivity.class;
                        case 53:
                            targetClass = RoundActivity.class;
                            break;
                        case 54:
                            targetClass = RangSeekBarActivity.class;
                            break;
                        case 55:
                            targetClass = WebviewActivity.class;
                            break;
                        case 56:
                            targetClass = AnimatorActivity.class;
                            break;
                        case 57:
                            targetClass = TsetSVGAnimatorActivity.class;
                            break;
                        case 58:
                            targetClass = TestBaseAdapterActivity.class;
                            break;
                        case 59:
                            targetClass = TestCenterActivity.class;
                            break;
                        case 60:
                            targetClass = TestOptionsActivity.class;
                            break;
                        case 61:
                            targetClass = ActivityOptionsActivity.class;
                            break;
                        case 62:
                            targetClass = TypeListViewActivity.class;
                            break;
                        case 63:
                            targetClass = PaintXfermodeActivity.class;
                            break;
                        default:
                            break;
                    }

                    Intent intent = new Intent(MainActivity.this, targetClass);
                    if(position == 0){
                        intent.putExtra("111","111");
                    }
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
            }
        });

        final GestureDetector detector = new GestureDetector(this,new Defralut());
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return false;
            }
        });


        testPackageInfo();

        newThread();

//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if(newState == RecyclerView.SCROLL_STATE_IDLE){
//                    layout.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                layout.setVisibility(View.GONE);
//            }
//        });
//
//        Handler handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//            }
//        };
//
    }

    void testPackageInfo(){
        PackageManager manager = getPackageManager();
        try {
            Log.e(this.getClass().getSimpleName(),getPackageName());
            ApplicationInfo info =  manager.getApplicationInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }




    }

    private  class Defralut extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            layout.setVisibility(View.GONE);
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

    }

    private void test(){
        Button button = new Button(this);
        button.setText("Wefwf");
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.flags = WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN;
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        params.gravity = Gravity.RIGHT|Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.x = 100;
        params.y = 300;
        manager.addView(button,params);



    }




    private void hhii(){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(" bnrnboirb b ")
                .build();
        try {
            Response response = client.newCall(request).execute();
                System.out.println("OkHttpClient===>>"+response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    private void newThread(){



        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler     handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what){
                            case 0:
                                System.out.println("111111111111");
                                Toast.makeText(MainActivity.this,"iiiii",Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                };
                handler.sendEmptyMessage(0);
                Looper.loop();

            }
        }).start();


    }


    public static final String TAG_EXIT = "exit";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(intent != null){
            boolean isExit = intent.getBooleanExtra(TAG_EXIT,false);
            if(isExit){
                this.finish();
            }
        }
    }

//    static class DelayRunnable implements Runnable{
//        private WeakReference<Context> context;
//        public void initSplashFragment(){
//            FragmentManager manager = this.getFragmentManager();
//            FragmentTransaction transaction =  manager.beginTransaction();
//            SplashFragment fragment = new SplashFragment();
//            transaction.replace(R.id.frame,fragment);
//            transaction.commit();
//            new Handler().postDelayed(new DelayRunnable(this,fragment),3000);
//
//        }

//        private WeakReference<SplashFragment> fragment;
//
//        public DelayRunnable(Context context,SplashFragment splashFragment){
//            this.context = new WeakReference<Context>(context);
//            this.fragment = new WeakReference<SplashFragment>(splashFragment);
//            isshow = true;
//
//        }
//        @Override
//        public void run() {
//            Activity activity = (Activity) context.get();
//            SplashFragment splashFragment = fragment.get();
//            if(splashFragment == null) return;
//
//            FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
//            SplashFragment fragment = this.fragment.get();
//            transaction.remove(fragment);
//            transaction.commit();
//
//        }
//
//
//
//
//    }




    public  void removeFragment(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        SplashFragment fragment = new SplashFragment();
        transaction.remove(fragment);
        transaction.commit();

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("2222","onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        savedInstanceState.getString("2222");
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void initDatas(){
        mDatas = new ArrayList<String>();
        for(int i = 0;i<str.length;i++){
            mDatas.add(str[i]);
        }

    }


    public class MyHandler extends Handler{

        public MyHandler(Looper looper){
               super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }


    private void testNetwork() {
        try {
            URL url = new URL("http://www.baidu.com");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String lines = null;
            StringBuffer sb = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                sb.append(lines);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
