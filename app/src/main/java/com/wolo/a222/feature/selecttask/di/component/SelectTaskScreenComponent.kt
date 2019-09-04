package com.wolo.a222.feature.selecttask.di.component

import com.wolo.a222.feature.common.di.Scope.PerScreen
import com.wolo.a222.feature.selecttask.di.module.SelectTaskScreenModule
import com.wolo.a222.feature.selecttask.view.SelectTaskFragment
import dagger.Subcomponent

@Subcomponent(modules = [SelectTaskScreenModule::class])
@PerScreen
interface SelectTaskScreenComponent{
    fun inject(fragment: SelectTaskFragment)
}