package com.example.weili.practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.testinterface.LoginCallBack;
import com.testinterface.LoginInterface;
import com.testinterface.LoginInterfaceImpl;

public class PracticeMVPActivity extends AppCompatActivity implements LoginCallBack{

    private EditText username,pwd;
    private Button login;
    private LoginInterface mLoginInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_mvp);

        initView();
    }


    private void initView(){
        username = (EditText) findViewById(R.id.username);
        pwd= (EditText) findViewById(R.id.pwd);
        login =(Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mLoginInterface = new LoginInterfaceImpl();
                mLoginInterface.login(username.getText().toString(),
                        pwd.getText().toString(),PracticeMVPActivity.this);
            }
        });

    }

    @Override
    public void success() {

    }

    @Override
    public void failed() {

    }
}
