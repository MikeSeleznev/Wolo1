package com.wolo.a222.feature.task.di.component

import com.wolo.a222.feature.common.di.Scope.PerScreen
import com.wolo.a222.feature.selecttask.di.module.SelectTaskScreenModule
import com.wolo.a222.feature.selecttask.view.SelectTaskFragment
import com.wolo.a222.feature.task.di.module.TaskScreenModule
import com.wolo.a222.feature.task.view.TaskFragment
import dagger.Subcomponent

@Subcomponent(modules = [TaskScreenModule::class])
@PerScreen
interface TaskScreenComponent{
    fun inject(fragment: TaskFragment)
}