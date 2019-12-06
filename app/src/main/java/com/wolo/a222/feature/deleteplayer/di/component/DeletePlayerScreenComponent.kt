package com.wolo.a222.feature.deleteplayer.di.component

import com.wolo.a222.feature.common.di.scope.PerScreen
import com.wolo.a222.feature.deleteplayer.di.module.DeletePlayerScreenModule
import com.wolo.a222.feature.deleteplayer.view.DeletePlayerFragment
import dagger.Subcomponent

@Subcomponent(modules = [DeletePlayerScreenModule::class])
@PerScreen
interface DeletePlayerScreenComponent{
    fun inject(fragment: DeletePlayerFragment)
}