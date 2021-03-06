package com.wolo.a222.feature.task.di.component

import com.wolo.a222.feature.common.di.scope.PerFeature
import com.wolo.a222.feature.task.di.module.TaskFeatureModel
import dagger.Subcomponent

@Subcomponent(modules = [TaskFeatureModel::class])
@PerFeature
interface TaskFeatureComponent{

    fun plusTaskScreen(): TaskScreenComponent
}