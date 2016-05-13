package com.example.weili.practice;


import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraActivity extends AppCompatActivity {

    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }

    void initView(){
        surfaceView = (SurfaceView) findViewById(R.id.view);
        holder = surfaceView.getHolder();



    }


    void initCamera(){
        camera = Camera.open();
        try {
            camera.setPreviewDisplay(holder);
            Camera.Parameters parameters = camera.getParameters();
            //parameters.
            camera.setParameters(parameters);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
