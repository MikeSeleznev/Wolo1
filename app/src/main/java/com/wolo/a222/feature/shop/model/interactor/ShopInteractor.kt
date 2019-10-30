package com.wolo.a222.feature.shop.model.interactor

import android.app.Activity
import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Purchases
import com.wolo.a222.feature.common.entity.SkuDeck
import com.wolo.a222.feature.shop.presenter.ShopVM
import io.reactivex.Flowable

interface ShopInteractor{

    fun getPurchase(): Flowable<List<Purchases>>

    fun getSkuInfo(): Flowable<List<SkuDeck>>

    fun buyDeck(i: ShopVM, act: Activity)

    fun getPacks(): Flowable<List<Pack>>
}