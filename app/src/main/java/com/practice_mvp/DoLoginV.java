package com.practice_mvp;

/**
 * Created by wei.li on 2015/10/28.
 */
public class DoLoginV implements ToLogin,LonLosteter{


    private LoginV loginV;
    private OnLogin onLogin;

    public DoLoginV(LoginV loginV){
        this.loginV = loginV;
        onLogin = new OnLoginIpml();
    }

    @Override
    public void onClick(String name, String pwd) {

    }

    @Override
    public void nameError() {

    }

    @Override
    public void onSucess() {

    }

    @Override
    public void pwdError() {

    }
}
