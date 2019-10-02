package ru.ireca.kitchen.feature.auth.di.component

import com.wolo.a222.feature.auth.di.module.AuthFeatureModule
import com.wolo.a222.feature.common.di.Scope.PerFeature
import dagger.Subcomponent


@Subcomponent(modules = [AuthFeatureModule::class])
@PerFeature
interface AuthFeatureComponent {

    fun plusAuthScreen(): AuthScreenComponent

}
