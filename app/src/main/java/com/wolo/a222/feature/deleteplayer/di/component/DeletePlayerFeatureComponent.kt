package com.wolo.a222.feature.deleteplayer.di.component

import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.deleteplayer.di.module.DeletePlayerFeatureModel
import dagger.Subcomponent

@Subcomponent(modules = [DeletePlayerFeatureModel::class])
@PerFeature
interface DeletePlayerFeatureComponent{

    fun plusDeletePlayerScreen(): DeletePlayerScreenComponent
}