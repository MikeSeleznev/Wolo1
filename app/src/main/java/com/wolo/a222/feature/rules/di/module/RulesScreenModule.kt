package com.wolo.a222.feature.rules.di.module

import com.wolo.a222.feature.common.di.scope.PerScreen
import com.wolo.a222.feature.rules.presenter.RulesPresenter
import com.wolo.a222.feature.rules.presenter.RulesPresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RulesScreenModule {

    @Binds
    @PerScreen
    abstract fun provideRulesPresenter(presenter: RulesPresenterImpl): RulesPresenter
}