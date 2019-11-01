package com.wolo.a222.feature.auth.model.interactor

import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Purchases
import com.wolo.a222.feature.common.repository.WoloRepository
import io.reactivex.Flowable
import javax.inject.Inject

@PerFeature
class AuthInteractorImpl
@Inject
constructor(
    private val woloRepository: WoloRepository
) : AuthInteractor {

    override fun activateSuperUser() {
        game.superUser = true
    }

    override fun getPacks(): Flowable<List<Pack>> {
        return woloRepository.getPacks()
    }

    override fun getPurchases(): Flowable<List<Purchases>> {
        return woloRepository.getPurchases()
    }
}