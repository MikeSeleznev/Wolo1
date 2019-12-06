package com.wolo.a222.feature.auth.di.module

import com.wolo.a222.feature.auth.model.interactor.AuthInteractor
import com.wolo.a222.feature.auth.model.interactor.AuthInteractorImpl
import com.wolo.a222.feature.common.di.scope.PerFeature
import dagger.Module
import dagger.Provides


@Suppress("unused")
@Module
class AuthFeatureModule {

    @Provides
    @PerFeature
    fun bindAuthInteractor(Interactor: AuthInteractorImpl): AuthInteractor = Interactor

}
