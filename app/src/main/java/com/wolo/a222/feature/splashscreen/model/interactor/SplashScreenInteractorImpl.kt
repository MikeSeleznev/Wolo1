package com.wolo.a222.feature.auth.model.interactor

import com.wolo.a222.feature.common.model.repository.FB
import com.wolo.a222.feature.common.di.Scope.PerFeature
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerFeature
class SplashScreenInteractorImpl
@Inject
constructor(private val fB: FB) : SplashScreenInteractor {

    override fun loadPacks(): Completable {
        return fB.getPacks()
                .subscribeOn(Schedulers.io())
                .flatMapCompletable {
                    fB.addCardsToGame(it)
                    Completable.complete() }
    }
}
