package com.practice_mvp;

import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by wei.li on 2015/10/28.
 */
public class OnLoginIpml implements OnLogin{
    @Override
    public void onLogin(final String name, final String pwd, final LonLosteter lister) {

        new Handler().postDelayed(
                new Runnable() {
                    boolean error = false;
                    @Override
                    public void run() {
                        if(TextUtils.isEmpty(name)){
                            lister.nameError();
                            error = true;
                        }
                        if(TextUtils.isEmpty(pwd)){
                            lister.pwdError();
                            error = true;
                        }
                        if(!error){
                            lister.onSucess();
                        }
                    }
                },2000
        );
    }
}
