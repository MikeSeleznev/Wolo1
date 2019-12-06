package com.wolo.a222.feature.gamezone.di.component

import com.wolo.a222.feature.common.di.scope.PerFeature
import com.wolo.a222.feature.gamezone.di.module.GameZoneFeatureModule
import dagger.Subcomponent

@Subcomponent(modules = [GameZoneFeatureModule::class])
@PerFeature
interface GameZoneFeatureComponent {
    fun plusGameZoneScreen(): GameZoneScreenComponent
}