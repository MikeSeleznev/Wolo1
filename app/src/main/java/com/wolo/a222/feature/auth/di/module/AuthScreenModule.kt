package com.wolo.a222.feature.auth.di.module

import com.wolo.a222.feature.auth.presenter.AuthPresenter
import com.wolo.a222.feature.auth.presenter.AuthPresenterImpl
import com.wolo.a222.feature.common.di.Scope.PerScreen
import dagger.Binds
import dagger.Module


@Suppress("unused")
@Module
abstract class AuthScreenModule {

    @Binds
    @PerScreen
    abstract fun provideAuthPresenter(presenter: AuthPresenterImpl): AuthPresenter

}
