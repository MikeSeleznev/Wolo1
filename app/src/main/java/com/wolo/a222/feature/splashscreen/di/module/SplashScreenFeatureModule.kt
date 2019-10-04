package com.wolo.a222.feature.splashscreen.di.module

import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.splashscreen.model.interactor.SplashScreenInteractor
import com.wolo.a222.feature.splashscreen.model.interactor.SplashScreenInteractorImpl
import dagger.Module
import dagger.Provides


@Suppress("unused")
@Module
class SplashScreenFeatureModule {

    @Provides
    @PerFeature
    fun bindSplashScreenInteractor(Interactor: SplashScreenInteractorImpl): SplashScreenInteractor = Interactor

}
