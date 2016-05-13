package com.testinterface;

/**
 * Created by wei.li on 2016/4/27.
 */
public class LoginPreseter implements LoginCallBack {
    private UIview mUIview;
    private LoginInterface mInterface;

    public LoginPreseter(UIview uIview){
        this.mUIview = uIview;
        mInterface = new LoginInterfaceImpl();
    }

    private void toLogin(String name,String pwd){
        mUIview.show();
        mInterface.login(name,pwd,this);
    }


    @Override
    public void success() {
        mUIview.hide();
    }

    @Override
    public void failed() {
        mUIview.hide();
    }
}
