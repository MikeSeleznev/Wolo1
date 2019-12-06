package com.wolo.a222.feature.shop.di.module

import com.wolo.a222.feature.common.di.scope.PerScreen
import com.wolo.a222.feature.shop.presenter.ShopPresenter
import com.wolo.a222.feature.shop.presenter.ShopPresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ShopScreenModule {

    @Binds
    @PerScreen
    abstract fun provideShopPresenter(presenter: ShopPresenterImpl): ShopPresenter
}