package com.wolo.a222.feature.auth.di.module

import com.wolo.a222.feature.auth.model.interactor.SplashScreenInteractor
import com.wolo.a222.feature.auth.model.interactor.SplashScreenInteractorImpl
import com.wolo.a222.feature.common.di.Scope.PerFeature
import dagger.Module
import dagger.Provides


@Suppress("unused")
@Module
class SplashScreenFeatureModule {

    @Provides
    @PerFeature
    fun bindSplashScreenInteractor(Interactor: SplashScreenInteractorImpl): SplashScreenInteractor = Interactor

}
