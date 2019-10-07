package com.wolo.a222.feature.selecttask.model.interactor

import com.android.billingclient.api.Purchase
import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.model.sku.SkuDeck
import io.reactivex.Flowable

interface SelectTaskInteractor {

    fun setChoosedPack(p: Pack)

    fun getPurchase(): Flowable<MutableList<Purchase>>

    fun getSkuInfo(idList : List<String>): Flowable<List<SkuDeck>>
}