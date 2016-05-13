package com.example.weili.practice;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeImageTransform;
import android.widget.ImageView;
import android.widget.TextView;

import com.liwei.adapter.ImagesAdapter;
import com.liwei.entity.Image;
import com.liwei.util.ImageFilesCreateLoader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ActivityOptionsActivity extends AppCompatActivity  implements ImagesAdapter.ImagesAdapterCallback{

    private final static String TAG = ActivityOptionsActivity.class.getSimpleName().toString();
    private TextView title;
    private RecyclerView mRecyclerView;
    private final List<Image> mImageList = new ArrayList<>();
    private Picasso mPicasso;
    private ImagesAdapter mAdapter;
    private static final int SPAN_COUNT = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_options);

        mPicasso = Picasso.with(this);
        mAdapter = new ImagesAdapter(this,mImageList,mPicasso,SPAN_COUNT);
        initView();

        getLoaderManager().initLoader(0,null,new ImageFilesCreateLoader(this, new ImageFilesCreateLoader.LoadFinishedCallback() {
            @Override
            public void onLoadFinished(List<Image> imagesList) {
                mImageList.addAll(imagesList);
                mAdapter.notifyDataSetChanged();
            }
        })).forceLoad();


    }


    private void initView(){
        title = (TextView)findViewById(R.id.title);
        title.setText(TAG);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,SPAN_COUNT));
        mRecyclerView.setAdapter(mAdapter);


    }


    @Override
    public void enterImageDetails(String sharedImageTransitionName, File imageFile, ImageView image, Image imageModel) {
     //   ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,image,sharedImageTransitionName);
        int[] los = new int[2];
        image.getLocationOnScreen(los);

        ActivityOptions options1 = ActivityOptions.makeScaleUpAnimation(image,image.getWidth()/2,image.getHeight()/2,0,0);
        getWindow().setSharedElementEnterTransition(new ChangeImageTransform(this,null));

        Intent intent = ImageDetailActivity.getStartIntent(this,sharedImageTransitionName,imageFile);
        startActivity(intent,options1.toBundle());
    }
}
