package com.com.obser

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

/**
 * Created by wei.li on 2016/2/22.
 */
class LiWei : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var message = TextView(this);
        message.setText("Hello");
        setContentView(message);
    }


}