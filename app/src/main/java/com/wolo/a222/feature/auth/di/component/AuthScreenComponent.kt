package com.wolo.a222.feature.auth.di.component

import com.wolo.a222.feature.auth.di.module.AuthScreenModule
import com.wolo.a222.feature.auth.view.AuthFragment
import com.wolo.a222.feature.common.di.scope.PerScreen
import dagger.Subcomponent


@Subcomponent(modules = [AuthScreenModule::class])
@PerScreen
interface AuthScreenComponent {

    fun inject(fragment: AuthFragment)

}
