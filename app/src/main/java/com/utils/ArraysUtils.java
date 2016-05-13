package com.utils;

/**
 * Created by wei.li on 2016/2/25.
 */
public class ArraysUtils {

    private static class List<E>{

        private  E[] a;

        public boolean contains(Object o){
            if(o != null){
                for(E e : a){
                    if(e.equals(o)){
                        return true;
                    }
                }
            }else{
                for(E e :a){
                    if(e == null){
                        return true;
                    }
                }
            }

            return false;
        }


    }



}
