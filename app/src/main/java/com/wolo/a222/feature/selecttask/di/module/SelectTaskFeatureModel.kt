package com.wolo.a222.feature.selecttask.di.module

import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.selecttask.model.interactor.SelectTaskInteractor
import com.wolo.a222.feature.selecttask.model.interactor.SelectTaskInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class SelectTaskFeatureModel{

    @Provides
    @PerFeature
    fun bindSelectTaskInteractor(Interactor: SelectTaskInteractorImpl): SelectTaskInteractor = Interactor
}