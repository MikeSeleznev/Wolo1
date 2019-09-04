package com.wolo.a222

import android.app.Application
import com.wolo.a222.feature.common.di.injector.AppInjector
import com.wolo.a222.feature.common.di.injector.Injector
import com.wolo.a222.feature.common.di.injector.InjectorProvider
import com.wolo.a222.feature.common.model.Game

class WoloApp: Application(), InjectorProvider {

    override lateinit var injector: Injector

    companion object {
        var game = Game()
    }

    override fun onCreate() {
        super.onCreate()
        injector = AppInjector(this)
        injector.getAppComponent().inject(this)

    }
}

