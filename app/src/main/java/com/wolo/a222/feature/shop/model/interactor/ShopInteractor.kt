package com.wolo.a222.feature.shop.model.interactor

import com.android.billingclient.api.Purchase
import com.wolo.a222.model.sku.Sku
import com.wolo.a222.model.sku.SkuDeck
import io.reactivex.Flowable

interface ShopInteractor{

    fun getPurchase(): Flowable<MutableList<Purchase>>

    fun getSkuInfo(): Flowable<List<SkuDeck>>
}