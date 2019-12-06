package com.wolo.a222.feature.selecttask.di.component

import com.wolo.a222.feature.common.di.scope.PerFeature
import com.wolo.a222.feature.selecttask.di.module.SelectTaskFeatureModel
import dagger.Subcomponent

@Subcomponent(modules = [SelectTaskFeatureModel::class])
@PerFeature
interface SelectTaskFeatureComponent{
    fun plusSelectTaskScreen(): SelectTaskScreenComponent
}