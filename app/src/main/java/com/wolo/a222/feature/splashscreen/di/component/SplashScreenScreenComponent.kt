package com.wolo.a222.feature.splashscreen.di.component


import com.wolo.a222.feature.splashscreen.di.module.SplashScreenScreenModule
import com.wolo.a222.feature.common.di.scope.PerScreen
import com.wolo.a222.feature.splashscreen.view.SplashScreenFragment
import dagger.Subcomponent


@Subcomponent(modules = [SplashScreenScreenModule::class])
@PerScreen
interface SplashScreenScreenComponent {

    fun inject(fragment: SplashScreenFragment)

}
