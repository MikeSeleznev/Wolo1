package com.wolo.a222.feature.shop.model.interactor

import android.app.Activity
import com.android.billingclient.api.Purchase
import com.wolo.a222.feature.common.entity.SkuDeck
import io.reactivex.Flowable

interface ShopInteractor{

    fun getPurchase(): Flowable<MutableList<Purchase>>

    fun getSkuInfo(): Flowable<List<SkuDeck>>

    fun buyDeck(i: SkuDeck, act: Activity)
}