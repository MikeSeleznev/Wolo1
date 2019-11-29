package com.wolo.a222.feature.rules.di.component

import com.wolo.a222.feature.common.di.scope.PerScreen
import com.wolo.a222.feature.rules.di.module.RulesScreenModule
import com.wolo.a222.feature.rules.view.RulesFragment
import dagger.Subcomponent

@Subcomponent(modules = [RulesScreenModule::class])
@PerScreen
interface RulesScreenComponent{
    fun inject(fragment: RulesFragment)
}