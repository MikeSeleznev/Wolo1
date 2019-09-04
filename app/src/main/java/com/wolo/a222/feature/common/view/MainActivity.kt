package com.wolo.a222.feature.common.view

import android.os.Bundle
import com.wolo.a222.R


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
           navigator.onStartMainActivity()
        }
    }


}
