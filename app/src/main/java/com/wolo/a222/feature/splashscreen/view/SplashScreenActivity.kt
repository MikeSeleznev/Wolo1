package com.wolo.a222.feature.splashscreen.view

import android.os.Bundle
import com.wolo.a222.R
import com.wolo.a222.feature.common.view.BaseActivity

class SplashScreenActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        if (savedInstanceState == null){
            navigator.onStart()
        }
    }

}