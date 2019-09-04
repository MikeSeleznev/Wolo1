package ru.ireca.kitchen.feature.auth.di.component

import com.wolo.a222.feature.auth.di.module.SplashScreenFeatureModule
import com.wolo.a222.feature.common.di.Scope.PerFeature
import dagger.Subcomponent



@Subcomponent(modules = [SplashScreenFeatureModule::class])
@PerFeature
interface SplashScreenFeatureComponent {

    fun plusSplashScreenScreen(): SplashScreenScreenComponent

}
