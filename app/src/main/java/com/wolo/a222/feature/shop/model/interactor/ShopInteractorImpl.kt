package com.wolo.a222.feature.shop.model.interactor

import android.app.Activity
import android.content.Context
import com.android.billingclient.api.Purchase
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.market.Billing
import com.wolo.a222.model.sku.SkuDeck
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerFeature
class ShopInteractorImpl @Inject constructor(
        private val context: Context,
        private val billing: Billing
) : ShopInteractor{

    override fun getPurchase(): Flowable<MutableList<Purchase>> {
       return billing.getPurchases(context)
               .map {listPurchases ->
                   val list = mutableListOf<Purchase>()
                   val packs = game.packs.map {pack ->
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

    override fun getSkuInfo(): Flowable<List<SkuDeck>> {
        val idList = mutableListOf<String>()
        game.packs.map {pack ->
            if (pack.id != ""){
                idList.add(pack.id)
            }
        }
        return billing.getSkuInfo(context, idList)
               .subscribeOn(Schedulers.io())
    }

    override fun buyDeck(i: Int) {
       billing.buyDeck(i, context)
    }
}