package com.wolo.a222.feature.common.storage

import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Purchases
import com.wolo.a222.feature.common.entity.SkuDeck
import com.wolo.a222.feature.common.repository.WoloRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class WoloRepositoryImpl @Inject constructor(
    private val database: WoloDatabase
) : WoloRepository {

    override fun setPacks(packs: List<Pack>): Single<List<Long>> = Single.fromCallable {
        database.PackDao().insert(packs)
    }

    override fun setSkuDecks(skuDeck: List<SkuDeck>): Single<List<Long>> = Single.fromCallable {
        database.SkuDeckDao().insert(skuDeck)
    }

    override fun getSkuDecks(): Flowable<List<SkuDeck>> {
        return database.SkuDeckDao().getSkuDecks()
    }

    override fun getPacks(): Flowable<List<Pack>> {
        return database.PackDao().getPacks()
    }

    override fun setPurchases(purchases: List<Purchases>): Single<List<Long>>  = Single.fromCallable{
        database.PurchasesDao().insert(purchases)
    }

    override fun getPurchases(): Flowable<List<Purchases>> {
        return database.PurchasesDao().getPurchases()
    }

    override fun deletePacks() {
        database.PackDao().deleteAll()
    }

    override fun deleteSkuDecks() {
       database.SkuDeckDao().deleteAll()
    }

    override fun deletePurchases() {
        database.PurchasesDao().deleteAll()
    }
}
