package com.liwei.adapter;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.weili.practice.R;
import com.liwei.entity.Image;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by wei.li on 2016/4/19.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagedsViewHolder> {


    private final ImagesAdapterCallback mAdapterCallback;
    private final List<Image> mImageList;
    private final Picasso mPicasso;
    private final int mSpanCount;


    public ImagesAdapter(ImagesAdapterCallback imagesAdapterCallback, List<Image> imagesList, Picasso imageDownloader, int spanCount){
        mAdapterCallback = imagesAdapterCallback;
        mImageList = imagesList;
        mPicasso = imageDownloader;
        mSpanCount = spanCount;
    }

    public static String createSharedImageTransitionName(int position){
        return "SharedImage" + position;
    }


    @Override
    public int getItemCount() {
        return mImageList.size();
    }


    @Override
    public void onBindViewHolder(final ImagedsViewHolder holder, int position) {

        final Image image = mImageList.get(position);

        final String sharedImageTransitionName = createSharedImageTransitionName(position);
        ViewCompat.setTransitionName(holder.image,sharedImageTransitionName);

        if(image.isVisible()){
            mPicasso.load(image.imageFile).into(holder.image);
            holder.image.setVisibility(View.VISIBLE);
        }else{
            holder.image.setVisibility(View.INVISIBLE);
        }


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapterCallback.enterImageDetails(sharedImageTransitionName,image.imageFile,holder.image,image);
            }
        });
    }

    @Override
    public ImagedsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) item.getLayoutParams();
        params.height = item.getResources().getDisplayMetrics().widthPixels/mSpanCount;
         ImagedsViewHolder viewHolder = new ImagedsViewHolder(item);
        if(viewType == 0) {
            viewHolder.image.setScaleType(ImageView.ScaleType.CENTER);
        }

        if(viewType == 1) {
            viewHolder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        if(viewType == 2) {
            viewHolder.image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }

        if(viewType == 3) {
            viewHolder.image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }

        if(viewType == 4) {
            viewHolder.image.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        if(viewType == 5) {
            viewHolder.image.setScaleType(ImageView.ScaleType.MATRIX);
        }
        return viewHolder;
    }


    @Override
    public int getItemViewType(int position) {
        if(position % 6 == 0) {
            return 0;
        }

        if(position % 5 == 0) {
            return 1;
        }

        if(position % 4 == 0) {
            return 2;
        }

        if(position % 3 == 0) {
            return 3;
        }

        if(position % 2 == 0) {
            return 4;
        }

        return 5;
    }

    public interface ImagesAdapterCallback{
        void enterImageDetails(String sharedImageTransitionName, File imageFile, ImageView image, Image imageModel);
    }

    public static class ImagedsViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        public ImagedsViewHolder(View itemView){
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
