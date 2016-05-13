package com.liwei.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;

/**
 * Created by wei.li on 2016/4/19.
 */
public class Image implements Parcelable {

    public final int imageId;
    public final File imageFile;

    public boolean mImageIsVisible = true;

    public Image(int imageId,File imageFile){
        this.imageId = imageId;
        this.imageFile = imageFile;
    }

    protected Image(Parcel source){
        imageId = source.readInt();
        imageFile = (File)source.readSerializable();
        mImageIsVisible =source.readByte() !=0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
           dest.writeInt(imageId);
           dest.writeSerializable(imageFile);
           dest.writeByte((byte)(mImageIsVisible?1:0));
    }


    public  boolean isVisible(){
        return mImageIsVisible;
    }

   public void setVisibility(boolean isVisible){
         mImageIsVisible = isVisible;
    }

    public static final Creator<Image> CREATOR = new Creator<Image>(){
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

}
