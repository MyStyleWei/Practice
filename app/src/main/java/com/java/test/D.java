package com.java.test;

/**
 * Created by wei.li on 2015/11/2.
 */
public class D implements A,B,C{


    @Override
    public void testA() {
        System.out.println("A");
    }

    @Override
    public void testB() {
        System.out.println("B");
    }

    @Override
    public void testC() {
        System.out.println("C");
    }
}
