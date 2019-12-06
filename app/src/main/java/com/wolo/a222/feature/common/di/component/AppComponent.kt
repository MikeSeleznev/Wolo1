package com.wolo.a222.feature.common.di.component

import com.wolo.a222.WoloApp
import com.wolo.a222.feature.common.di.scope.PerApplication
import com.wolo.a222.feature.common.di.module.AppModule
import com.wolo.a222.feature.common.di.module.DataModule
import com.wolo.a222.feature.common.view.BaseActivity
import com.wolo.a222.feature.deleteplayer.di.component.DeletePlayerFeatureComponent
import com.wolo.a222.feature.gamezone.di.component.GameZoneFeatureComponent
import com.wolo.a222.feature.rules.di.component.RulesFeatureComponent
import com.wolo.a222.feature.selecttask.di.component.SelectTaskFeatureComponent
import com.wolo.a222.feature.shop.di.component.ShopFeatureComponent
import com.wolo.a222.feature.splashscreen.di.component.SplashScreenFeatureComponent
import com.wolo.a222.feature.task.di.component.TaskFeatureComponent
import dagger.Component
import com.wolo.a222.feature.auth.di.component.AuthFeatureComponent


@PerApplication
@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent {

    fun inject(application: WoloApp)

    fun inject(activity: BaseActivity)

    fun plusAuthFeature(): AuthFeatureComponent

    fun plusSplashScreenFeature(): SplashScreenFeatureComponent

    fun plusGameZoneFeature(): GameZoneFeatureComponent

    fun plusSelectTaskFeature(): SelectTaskFeatureComponent

    fun plusTaskFeature(): TaskFeatureComponent

    fun plusShopFeature(): ShopFeatureComponent

    fun plusRulesFeature(): RulesFeatureComponent

    fun plusDeletePlayerFeature(): DeletePlayerFeatureComponent

}