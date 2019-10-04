package com.wolo.a222.feature.splashscreen.di.component

import com.wolo.a222.feature.splashscreen.di.module.SplashScreenFeatureModule
import com.wolo.a222.feature.common.di.Scope.PerFeature
import dagger.Subcomponent


@Subcomponent(modules = [SplashScreenFeatureModule::class])
@PerFeature
interface SplashScreenFeatureComponent {

    fun plusSplashScreenScreen(): SplashScreenScreenComponent

}
