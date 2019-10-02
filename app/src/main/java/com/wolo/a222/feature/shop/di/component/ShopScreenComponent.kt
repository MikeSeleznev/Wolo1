package com.wolo.a222.feature.shop.di.component

import com.wolo.a222.feature.common.di.Scope.PerScreen
import com.wolo.a222.feature.shop.di.module.ShopScreenModule
import com.wolo.a222.feature.shop.view.ShopFragment
import dagger.Subcomponent

@Subcomponent(modules = [ShopScreenModule::class])
@PerScreen
interface ShopScreenComponent{
    fun inject(fragment: ShopFragment)
}