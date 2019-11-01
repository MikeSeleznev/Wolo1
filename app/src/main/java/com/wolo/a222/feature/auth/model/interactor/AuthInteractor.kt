package com.wolo.a222.feature.auth.model.interactor

import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Purchases
import io.reactivex.Flowable


interface AuthInteractor {

    fun activateSuperUser()

    fun getPacks(): Flowable<List<Pack>>

    fun getPurchases(): Flowable<List<Purchases>>
}