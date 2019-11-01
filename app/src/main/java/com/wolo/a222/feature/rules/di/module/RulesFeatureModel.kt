package com.wolo.a222.feature.rules.di.module

import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.rules.model.interactor.RulesInteractor
import com.wolo.a222.feature.rules.model.interactor.RulesInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class RulesFeatureModel{

    @Provides
    @PerFeature
    fun bindRulesInteractor(Interactor: RulesInteractorImpl): RulesInteractor = Interactor
}