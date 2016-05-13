package com.com.kotlin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wei.li on 2016/2/26.
 */
public class TestKotlin {

    public static void  main(String args[]){

        String[] list = {"111","222"};
        System.out.println(PracticeKotlinKt.sum(5,8)+"");
        System.out.println(PracticeKotlinKt.sum1(5,8)+"");
        PracticeKotlinKt.sum2(5,8);
        PracticeKotlinKt.sum3(5,8);
  //      PracticeKotlinKt.array(list);
   //     PracticeKotlinKt.nullable(list);

        PracticeKotlinKt.getStringLength("234764");
        PracticeKotlinKt.getStringLength("sss");
        PracticeKotlinKt.loop(list);
        PracticeKotlinKt.whileLoop(list);
        PracticeKotlinKt.main();
        PracticeKotlinKt.testLazy();
    }
}
