package ru.ireca.kitchen.feature.auth.di.component


import com.wolo.a222.feature.auth.di.module.SplashScreenScreenModule
import com.wolo.a222.feature.common.di.Scope.PerScreen
import com.wolo.a222.feature.splashscreen.view.SplashScreenFragment
import dagger.Subcomponent


@Subcomponent(modules = [SplashScreenScreenModule::class])
@PerScreen
interface SplashScreenScreenComponent {

    fun inject(fragment: SplashScreenFragment)

}
