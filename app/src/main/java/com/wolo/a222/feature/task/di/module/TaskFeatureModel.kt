package com.wolo.a222.feature.task.di.module

import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.task.model.interactor.TaskInteractor
import com.wolo.a222.feature.task.model.interactor.TaskInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class TaskFeatureModel{

    @Provides
    @PerFeature
    fun bindTaskInteractor(Interactor: TaskInteractorImpl): TaskInteractor = Interactor
}