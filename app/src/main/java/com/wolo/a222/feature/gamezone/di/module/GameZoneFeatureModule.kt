package com.wolo.a222.feature.gamezone.di.module

import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.gamezone.model.interactor.GameZoneInteractor
import com.wolo.a222.feature.gamezone.model.interactor.GameZoneInteractorImpl
import dagger.Module
import dagger.Provides


@Module
class GameZoneFeatureModule {

    @Provides
    @PerFeature
    fun bindGameZoneInteractor(Interactor: GameZoneInteractorImpl): GameZoneInteractor = Interactor
}