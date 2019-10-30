package com.wolo.a222.feature.common.repository

import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.SkuDeck
import com.wolo.a222.model.sku.Sku
import io.reactivex.Flowable
import io.reactivex.Single

interface WoloRepository {

    fun setPacks(packs: List<Pack>): Single<List<Long>>

    fun getPacks(): Flowable<List<Pack>>

    fun setSkuDecks(skuDeck: List<SkuDeck>): Single<List<Long>>

    fun getSkuDecks(): Flowable<List<SkuDeck>>
}