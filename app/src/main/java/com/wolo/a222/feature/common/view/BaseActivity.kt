package com.wolo.a222.feature.common.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wolo.a222.feature.common.di.injector.Injector
import com.wolo.a222.feature.common.di.injector.InjectorProvider
import com.wolo.a222.feature.common.navigation.Navigator
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    protected lateinit var navigator: Navigator

    private val injector: Injector
        get() = (applicationContext as InjectorProvider).injector

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        navigator.attachActivity(this)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        navigator.detachActivity(this)
        super.onStop()
    }

    override fun onBackPressed() {
        /* if (!navigator.onBackPressed()) {
             super.onBackPressed()
         }*/
    }

}