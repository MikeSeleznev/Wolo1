package com.wolo.a222.feature.deleteplayer.di.module

import com.wolo.a222.feature.common.di.scope.PerFeature
import com.wolo.a222.feature.deleteplayer.model.interactor.DeletePlayerInteractor
import com.wolo.a222.feature.deleteplayer.model.interactor.DeletePlayerInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class DeletePlayerFeatureModel{

    @Provides
    @PerFeature
    fun bindDeletePlayerInteractor(Interactor: DeletePlayerInteractorImpl): DeletePlayerInteractor = Interactor
}