package com.wolo.a222.feature.selecttask.model.interactor

import android.content.Context
import com.android.billingclient.api.Purchase
import com.wolo.a222.WoloApp
import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.selecttask.presenter.SelectTaskVM
import com.wolo.a222.market.Billing
import com.wolo.a222.model.sku.SkuDeck
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerFeature
class SelectTaskInteractorImpl @Inject constructor(
        private val context: Context,
        private val billing: Billing
) : SelectTaskInteractor {

    override fun setChoosedPack(p: SelectTaskVM) {
        WoloApp.game.choosedPack = p
    }

    override fun getPurchase(): Flowable<MutableList<Purchase>> {
        return billing.getPurchases(context)
                .map {listPurchases ->
                    val list = mutableListOf<Purchase>()
                    val packs = WoloApp.game.packs.map { pack ->
                        pack.id
                    }
                    listPurchases.map {purchase ->
                        for (i in packs){
                            if (i == purchase.sku){
                                list.add(purchase)
                            }
                        }
                    }
                    list
                }
                .subscribeOn(Schedulers.io())

    }

    override fun getSkuInfo(idList: List<String>): Flowable<List<SkuDeck>> {
        val iList = mutableListOf<String>()
        WoloApp.game.packs.map { pack ->
            if (pack.id != ""){
                iList.add(pack.id)
            }
        }
        return billing.getSkuInfo(context, iList)
                .subscribeOn(Schedulers.io())
    }
}