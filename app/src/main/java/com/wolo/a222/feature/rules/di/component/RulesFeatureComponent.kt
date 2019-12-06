package com.wolo.a222.feature.rules.di.component

import com.wolo.a222.feature.common.di.scope.PerFeature
import com.wolo.a222.feature.rules.di.module.RulesFeatureModel
import dagger.Subcomponent

@Subcomponent(modules = [RulesFeatureModel::class])
@PerFeature
interface RulesFeatureComponent{

    fun plusRulesScreen(): RulesScreenComponent
}