package com.wolo.a222.feature.shop.model.interactor

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import com.wolo.a222.feature.common.di.scope.PerFeature
import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Purchases
import com.wolo.a222.feature.common.entity.SkuDeck
import com.wolo.a222.feature.common.repository.WoloRepository
import com.wolo.a222.feature.shop.presenter.ShopVM
import com.wolo.a222.feature.common.billing.Billing
import io.reactivex.Flowable
import javax.inject.Inject

@PerFeature
class ShopInteractorImpl @Inject constructor(
    private val context: Context,
    private val billing: Billing,
    private val woloRepository: WoloRepository
) : ShopInteractor{

    override fun getPurchase(): Flowable<List<Purchases>> {
       return woloRepository.getPurchases()

    }

    override fun getSkuInfo(): Flowable<List<SkuDeck>> {
        return woloRepository.getSkuDecks()
    }

    override fun getPacks(): Flowable<List<Pack>> {
        return woloRepository.getPacks()
    }

    @SuppressLint("CheckResult")
    override fun buyDeck(i: ShopVM, act: Activity) {
        woloRepository.getPacks()
            .flatMap {list->
                val listId = list.map { it.id }
                billing.getSkuInfo(context, listId)
            }.subscribe {
                it.find { skuDetails ->
                    skuDetails.sku == i.id
                }.let {it->
                    if (it != null) {
                        billing.buyDeck(it, context, act)
                    }
                }
            }

       /* woloRepository.getSkuDecks().blockingFirst().findLast {skuDetails ->
            skuDetails.skuType == i.id
        }.let {it->
            if (it != null) {
                billing.buyDeck(it, context, act)
            }
        }*/
    }
}