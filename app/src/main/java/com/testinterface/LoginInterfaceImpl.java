package com.testinterface;

import android.os.Handler;
import android.os.Message;

/**
 * Created by wei.li on 2016/4/27.
 */
public class LoginInterfaceImpl implements LoginInterface {



    @Override
    public void login(final String username, final String pwd, final LoginCallBack callBack) {


        new Thread(new Runnable() {
            @Override
            public void run() {

                if(username.equals("liwei") && pwd.equals("123456")){
                    callBack.success();
                    mHandler.obtainMessage(100).sendToTarget();
                }else{
                    callBack.failed();
                    mHandler.obtainMessage(404).sendToTarget();
                }

            }
        }).start();

    };

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.arg1 == 100){

            }else{

            }
        }
    };




}
