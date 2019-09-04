package com.wolo.a222.feature.gamezone.di.module

import com.wolo.a222.feature.common.di.Scope.PerScreen
import com.wolo.a222.feature.gamezone.presenter.GameZonePresenter
import com.wolo.a222.feature.gamezone.presenter.GameZonePresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class GameZoneScreenModule{

    @Binds
    @PerScreen
    abstract fun provideGameZonePresenter(presenter: GameZonePresenterImpl): GameZonePresenter

}


