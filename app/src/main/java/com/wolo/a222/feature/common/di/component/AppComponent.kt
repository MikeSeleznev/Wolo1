package com.wolo.a222.feature.common.di.component

import com.wolo.a222.WoloApp
import com.wolo.a222.feature.common.di.Scope.PerApplication
import com.wolo.a222.feature.common.di.module.AppModule
import com.wolo.a222.feature.common.view.BaseActivity
import com.wolo.a222.feature.gamezone.di.component.GameZoneFeatureComponent
import com.wolo.a222.feature.selecttask.di.component.SelectTaskFeatureComponent
import dagger.Component
import ru.ireca.kitchen.feature.auth.di.component.AuthFeatureComponent
import ru.ireca.kitchen.feature.auth.di.component.SplashScreenFeatureComponent
import javax.inject.Inject


@PerApplication
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(application: WoloApp)

    fun inject(activity: BaseActivity)

    fun plusAuthFeature(): AuthFeatureComponent

    fun plusSplashScreenFeature(): SplashScreenFeatureComponent

    fun plusGameZoneFeature(): GameZoneFeatureComponent

    fun plusSelectTaskFeature(): SelectTaskFeatureComponent
}