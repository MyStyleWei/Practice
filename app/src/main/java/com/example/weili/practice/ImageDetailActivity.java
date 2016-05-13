package com.example.weili.practice;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageDetailActivity extends AppCompatActivity {

    public static final String SHARED_ELEMENT_IMAGE_KEY = "SHARED_ELEMENT_IMAGE_KEY";
    public static final String IMAGE_FILE_KEY = "IMAGE_FILE_KEY";

    private ImageView mImage;

    private Picasso mImageDownloader;


    public static Intent getStartIntent(Activity activity,String  sharedImageTransitionName, File imageFile){
        Intent startIntent = new Intent(activity,ImageDetailActivity.class);
        startIntent.putExtra(SHARED_ELEMENT_IMAGE_KEY, sharedImageTransitionName);
        startIntent.putExtra(IMAGE_FILE_KEY, imageFile);
        return startIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);


        mImage = (ImageView) findViewById(R.id.enlarged_image);
        mImage.setVisibility(View.VISIBLE);

        String imageTransitionName = getIntent().getStringExtra(SHARED_ELEMENT_IMAGE_KEY);
        ViewCompat.setTransitionName(mImage, imageTransitionName);

        View mainContainer = findViewById(R.id.main_container);
        mainContainer.setAlpha(1.f);
        mImageDownloader = Picasso.with(this);

        File imageFile = (File) getIntent().getSerializableExtra(IMAGE_FILE_KEY);


        mImageDownloader.load(imageFile).into(mImage);
    }
}
