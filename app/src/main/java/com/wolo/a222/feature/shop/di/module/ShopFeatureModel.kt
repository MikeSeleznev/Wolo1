package com.wolo.a222.feature.shop.di.module

import com.wolo.a222.feature.common.di.scope.PerFeature
import com.wolo.a222.feature.shop.model.interactor.ShopInteractor
import com.wolo.a222.feature.shop.model.interactor.ShopInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class ShopFeatureModel{

    @Provides
    @PerFeature
    fun bindShopInteractor(Interactor: ShopInteractorImpl): ShopInteractor = Interactor
}