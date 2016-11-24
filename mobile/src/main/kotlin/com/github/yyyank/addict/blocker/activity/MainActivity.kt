package com.github.yyyank.addict.blocker.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Toast

/**
 * Created by yy_yank on 2016/11/23.
 */
class MainActivity : Activity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        Toast.makeText(this, "Hello My App!!!", Toast.LENGTH_LONG).show()
        super.onCreate(savedInstanceState)
    }

}