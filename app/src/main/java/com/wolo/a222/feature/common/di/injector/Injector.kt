package com.wolo.a222.feature.common.di.injector


import com.wolo.a222.feature.common.di.component.AppComponent
import com.wolo.a222.feature.gamezone.di.component.GameZoneFeatureComponent
import com.wolo.a222.feature.gamezone.di.component.GameZoneScreenComponent
import com.wolo.a222.feature.rules.di.component.RulesFeatureComponent
import com.wolo.a222.feature.rules.di.component.RulesScreenComponent
import com.wolo.a222.feature.selecttask.di.component.SelectTaskFeatureComponent
import com.wolo.a222.feature.selecttask.di.component.SelectTaskScreenComponent
import com.wolo.a222.feature.shop.di.component.ShopFeatureComponent
import com.wolo.a222.feature.shop.di.component.ShopScreenComponent
import com.wolo.a222.feature.splashscreen.di.component.SplashScreenFeatureComponent
import com.wolo.a222.feature.task.di.component.TaskFeatureComponent
import com.wolo.a222.feature.task.di.component.TaskScreenComponent
import ru.ireca.kitchen.feature.auth.di.component.AuthFeatureComponent
import ru.ireca.kitchen.feature.auth.di.component.AuthScreenComponent
import com.wolo.a222.feature.splashscreen.di.component.SplashScreenScreenComponent

interface Injector {

    fun getAppComponent(): AppComponent

    fun getAuthScreen(): AuthScreenComponent
    fun releaseAuthScreen()

    fun getAuthFeature(): AuthFeatureComponent
    fun releaseAuthFeature()

    fun getSplashScreenFeature(): SplashScreenFeatureComponent
    fun releaseSplashScreenFeature()

    fun getSplashScreenScreen(): SplashScreenScreenComponent
    fun releaseSplashScreenScreen()

    fun getGameZoneFeature(): GameZoneFeatureComponent
    fun releaseGameZoneFeature()

    fun getGameZoneScreen(): GameZoneScreenComponent
    fun releaseGameZoneScreen()

    fun getSelectTaskFeature(): SelectTaskFeatureComponent
    fun releaseSelectTaskFeature()

    fun getSelectTaskScreen(): SelectTaskScreenComponent
    fun releaseSelectTaskScreen()

    fun getTaskFeature(): TaskFeatureComponent
    fun releaseTaskFeature()

    fun getTaskScreen(): TaskScreenComponent
    fun releaseTaskScreen()

    fun getShopFeature(): ShopFeatureComponent
    fun releaseShopFeature()

    fun getShopScreen(): ShopScreenComponent
    fun releaseShopScreen()

    fun getRulesFeature(): RulesFeatureComponent
    fun releaseRulesFeature()

    fun getRulesScreen(): RulesScreenComponent
    fun releaseRulesScreen()
}