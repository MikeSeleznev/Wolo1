package ru.ireca.kitchen.feature.auth.di.component

import com.wolo.a222.feature.auth.view.AuthFragment
import com.wolo.a222.feature.common.di.Scope.PerScreen
import dagger.Subcomponent
import com.wolo.a222.feature.auth.di.module.AuthScreenModule


@Subcomponent(modules = [AuthScreenModule::class])
@PerScreen
interface AuthScreenComponent {

    fun inject(fragment: AuthFragment)

}
