package com.wolo.a222.feature.shop.di.component

import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.shop.di.module.ShopFeatureModel
import dagger.Subcomponent

@Subcomponent(modules = [ShopFeatureModel::class])
@PerFeature
interface ShopFeatureComponent{

    fun plusShopScreen(): ShopScreenComponent
}