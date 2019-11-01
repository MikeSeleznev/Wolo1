package com.wolo.a222.feature.rules.di.module

import com.wolo.a222.feature.common.di.Scope.PerScreen
import com.wolo.a222.feature.rules.presenter.RulesPresenter
import com.wolo.a222.feature.rules.presenter.RulesPresenterImpl
import com.wolo.a222.feature.shop.presenter.ShopPresenter
import com.wolo.a222.feature.shop.presenter.ShopPresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RulesScreenModule {

    @Binds
    @PerScreen
    abstract fun provideRulesPresenter(presenter: RulesPresenterImpl): RulesPresenter
}