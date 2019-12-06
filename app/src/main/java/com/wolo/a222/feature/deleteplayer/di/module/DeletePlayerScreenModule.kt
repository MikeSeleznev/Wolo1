package com.wolo.a222.feature.deleteplayer.di.module

import com.wolo.a222.feature.common.di.scope.PerScreen
import com.wolo.a222.feature.deleteplayer.presenter.DeletePlayerPresenter
import com.wolo.a222.feature.deleteplayer.presenter.DeletePlayerPresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DeletePlayerScreenModule {

    @Binds
    @PerScreen
    abstract fun provideDeletePlayerPresenter(presenter: DeletePlayerPresenterImpl): DeletePlayerPresenter
}