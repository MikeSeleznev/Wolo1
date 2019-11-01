package com.wolo.a222.feature.common.repository

import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Purchases
import com.wolo.a222.feature.common.entity.SkuDeck
import io.reactivex.Flowable
import io.reactivex.Single

interface WoloRepository {

    fun setPacks(packs: List<Pack>): Single<List<Long>>

    fun getPacks(): Flowable<List<Pack>>

    fun setSkuDecks(skuDeck: List<SkuDeck>): Single<List<Long>>

    fun getSkuDecks(): Flowable<List<SkuDeck>>

    fun setPurchases(purchases: List<Purchases>): Single<List<Long>>

    fun getPurchases(): Flowable<List<Purchases>>

    fun deletePacks()

    fun deleteSkuDecks()

    fun deletePurchases()
}