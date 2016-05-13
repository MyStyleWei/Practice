package com.example.weili.practice;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TestOptionsActivity extends AppCompatActivity {

    private Intent mIntent;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_options);
        btn = (Button) findViewById(R.id.fab_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(TestOptionsActivity.this,ElevationActivity.class);
                        startActivity(intent);
            }
        });
    }

    public void explode(View view){
        mIntent = new Intent(this,TestTransitionActivity.class);
        mIntent.putExtra("flag",0);
        startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void slide(View view){
        mIntent = new Intent(this,TestTransitionActivity.class);
        mIntent.putExtra("flag",1);
        startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void fade(View view){
        mIntent = new Intent(this,TestTransitionActivity.class);
        mIntent.putExtra("flag",2);
        startActivity(mIntent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void share(View view){
        View fab = findViewById(R.id.fab_button);
        mIntent = new Intent(this,TestTransitionActivity.class);
        mIntent.putExtra("flag",3);
        startActivity(mIntent,ActivityOptions.makeSceneTransitionAnimation(this,view,"share").toBundle());

    }
}
