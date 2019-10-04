package com.wolo.a222.feature.splashscreen.model.interactor

import io.reactivex.Completable


interface SplashScreenInteractor {

    fun loadPacks(): Completable

}