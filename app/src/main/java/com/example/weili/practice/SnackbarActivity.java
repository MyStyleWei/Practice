package com.example.weili.practice;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.com.liwei.widget.BottomSheetDialogView;
import com.jni.IMyAInterface;
import com.service.MyServivce;
import com.widget.caladen.CheckableTextView;


public class SnackbarActivity extends AppCompatActivity {

    private static  final String TAG = "SnackbarActivity...";
    private Button button;
    private EditText editText1;
    private EditText editText2;
    private int mDayNightMode = AppCompatDelegate.MODE_NIGHT_AUTO;
    private Toolbar toolbar;
    private ImageView imageView,img2;
    private LinearLayout mLayout;

    private CheckableTextView mTextView;



    private IMyAInterface mInterface;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mInterface  = IMyAInterface.Stub.asInterface(service);
            Log.e(TAG,"链接成功。。。");
            try {
                String s = mInterface.getInfo("我是Activity传入的字符串");
                Log.e(TAG,"service传进来的字符串。。。"+s);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG,"链接失败。。。");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        staetSericer();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               hideImg();
                BottomSheetDialogView.show(SnackbarActivity.this);
                Snackbar.make(v,"真的要点我？",Snackbar.LENGTH_LONG)
                        .setAction("真的", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(SnackbarActivity.this,"你真的点我了！",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        mLayout = (LinearLayout) findViewById(R.id.lin);
        img2 = (ImageView) findViewById(R.id.img2);

        imageView = (ImageView) findViewById(R.id.img) ;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.animate().scaleX(2).scaleY(2);
                img2.animate().scaleX(2).scaleY(2).start();
                mLayout.animate().rotation(10).start();
                Toast.makeText(SnackbarActivity.this,"111111",Toast.LENGTH_LONG).show();
    //            mLayout.animate().
            }
        });

        mTextView = (CheckableTextView) findViewById(R.id.text);
        mTextView.setOnCheckedListener(new CheckableTextView.OnCheckedListener() {
            @Override
            public void onCheckeListener(Checkable checkable) {
                mTextView.setText("the checkable is checked=="+checkable.isChecked());
                Log.e(TAG,checkable.isChecked()+"");

            }
        });

        mTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

//        editText1 = (EditText) findViewById(R.id.edit1);
//        editText2 = (EditText) findViewById(R.id.edit2);
//        editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                System.out.println(hasFocus);
//                if(!hasFocus){
//                    System.out.println(">>>>>>>"+hasFocus);
//                }
//            }
//        });

    }

    private void testValue(){
        ValueAnimator animator = ValueAnimator.ofInt(0,100)
                .setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                mTextView.scrollTo(0+(int)(100*fraction),100);
                testValue();
            }
        });
        animator.start();
    }

    private void hideImg(){
        imageView.setPivotX(imageView.getWidth()/2);
        imageView.setScaleY(imageView.getHeight());
        imageView.animate()
                .scaleX(0.1f)
                .scaleY(0.1f)
                .setDuration(150)
                .setStartDelay(100)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });

    }

    private void staetSericer(){
        Intent intent = new Intent(this, MyServivce.class);
        intent.putExtra("111","222");
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();


        int uiMode = getResources().getConfiguration().uiMode;
        int dayNightUiMode = uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (dayNightUiMode == Configuration.UI_MODE_NIGHT_NO) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_NO;
            button.setText("夜间模式");
        } else if (dayNightUiMode == Configuration.UI_MODE_NIGHT_YES) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_YES;
            button.setText("白天模式");
        } else {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_AUTO;
        }

    }

//    private void dayOrNightMode(){
//        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        recreate();
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_snackbar, menu);
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
