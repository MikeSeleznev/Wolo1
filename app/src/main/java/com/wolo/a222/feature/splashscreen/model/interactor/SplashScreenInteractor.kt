package com.wolo.a222.feature.splashscreen.model.interactor

import com.android.billingclient.api.SkuDetails
import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Purchases
import io.reactivex.Flowable
import io.reactivex.Single

interface SplashScreenInteractor {

    fun loadPacks(): Flowable<List<Pack>>

    fun loadSku(idList: List<String>): Flowable<List<SkuDetails>>

    fun loadPurchases(): Flowable<List<Purchases>>

    fun loadInfo(): Single<Long>

}