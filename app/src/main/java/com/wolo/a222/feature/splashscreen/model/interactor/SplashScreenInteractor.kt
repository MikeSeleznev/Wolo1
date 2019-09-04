package com.wolo.a222.feature.auth.model.interactor

import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


interface SplashScreenInteractor {

    fun loadPacks(): Completable

}