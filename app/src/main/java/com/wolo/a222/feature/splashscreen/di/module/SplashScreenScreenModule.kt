package com.wolo.a222.feature.splashscreen.di.module

import com.wolo.a222.feature.common.di.scope.PerScreen
import com.wolo.a222.feature.splashscreen.presenter.SplashScreenPresenter
import com.wolo.a222.feature.splashscreen.presenter.SplashScreenPresenterImpl
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class SplashScreenScreenModule {

    @Binds
    @PerScreen
    abstract fun provideSplashScreenPresenter(presenter: SplashScreenPresenterImpl): SplashScreenPresenter

}
