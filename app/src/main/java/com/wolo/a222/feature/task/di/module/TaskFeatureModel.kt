package com.wolo.a222.feature.task.di.module

import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.selecttask.model.Interactor.SelectTaskInteractor
import com.wolo.a222.feature.selecttask.model.Interactor.SelectTaskInteractorImpl
import com.wolo.a222.feature.task.model.Interactor.TaskInteractor
import com.wolo.a222.feature.task.model.Interactor.TaskInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class TaskFeatureModel{

    @Provides
    @PerFeature
    fun bindTaskInteractor(Interactor: TaskInteractorImpl): TaskInteractor = Interactor
}