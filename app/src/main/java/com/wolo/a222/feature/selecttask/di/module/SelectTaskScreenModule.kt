package com.wolo.a222.feature.selecttask.di.module

import com.wolo.a222.feature.common.di.Scope.PerScreen
import com.wolo.a222.feature.selecttask.presenter.SelectTaskPresenter
import com.wolo.a222.feature.selecttask.presenter.SelectTaskPresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class SelectTaskScreenModule {

    @Binds
    @PerScreen
    abstract fun provideSelectTaskPresenter(presenter: SelectTaskPresenterImpl): SelectTaskPresenter
}