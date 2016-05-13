package com.utils;

/**
 * Created by wei.li on 2016/2/3.
 */
public class NdkJniUtils {



   static{
      System.loadLibrary("liweiJni");
   }

   public native String getCLanguageString();
}
