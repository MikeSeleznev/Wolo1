package com.wolo.a222.feature.task.di.component

import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.selecttask.di.module.SelectTaskFeatureModel
import com.wolo.a222.feature.task.di.module.TaskFeatureModel
import dagger.Subcomponent

@Subcomponent(modules = [TaskFeatureModel::class])
@PerFeature
interface TaskFeatureComponent{

    fun plusTaskScreen(): TaskScreenComponent
}