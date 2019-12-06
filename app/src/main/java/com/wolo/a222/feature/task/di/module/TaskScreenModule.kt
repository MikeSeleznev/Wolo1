package com.wolo.a222.feature.task.di.module

import com.wolo.a222.feature.common.di.scope.PerScreen
import com.wolo.a222.feature.task.presenter.TaskPresenter
import com.wolo.a222.feature.task.presenter.TaskPresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class TaskScreenModule {

    @Binds
    @PerScreen
    abstract fun provideTaskPresenter(presenter: TaskPresenterImpl): TaskPresenter
}