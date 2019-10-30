package com.wolo.a222.feature.selecttask.model.interactor

import com.android.billingclient.api.Purchase
import com.wolo.a222.feature.selecttask.presenter.SelectTaskVM
import com.wolo.a222.feature.common.entity.SkuDeck
import io.reactivex.Flowable

interface SelectTaskInteractor {

    fun setChoosedPack(p: SelectTaskVM)

    fun getPurchase(): Flowable<MutableList<Purchase>>

    fun getSkuInfo(idList : List<String>): Flowable<List<SkuDeck>>
}