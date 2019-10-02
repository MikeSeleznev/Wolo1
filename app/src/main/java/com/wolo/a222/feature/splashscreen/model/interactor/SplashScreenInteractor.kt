package com.wolo.a222.feature.auth.model.interactor

import io.reactivex.Completable


interface SplashScreenInteractor {

    fun loadPacks(): Completable

}