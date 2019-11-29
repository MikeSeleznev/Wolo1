package com.wolo.a222.feature.gamezone.di.component

import com.wolo.a222.feature.common.di.scope.PerScreen
import com.wolo.a222.feature.gamezone.di.module.GameZoneScreenModule
import com.wolo.a222.feature.gamezone.view.GameZoneFragment
import dagger.Subcomponent

@Subcomponent(modules = [GameZoneScreenModule::class])
@PerScreen
interface GameZoneScreenComponent {
    fun inject(fragment: GameZoneFragment)
}