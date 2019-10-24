package com.wolo.a222.feature.auth.model.interactor

import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.di.Scope.PerFeature
import javax.inject.Inject

@PerFeature
class AuthInteractorImpl
@Inject
constructor(

) : AuthInteractor {

    override fun activateSuperUser() {
        game.superUser = true
    }
}