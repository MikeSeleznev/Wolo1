package com.wolo.a222.feature.splashscreen.model.interactor

import android.content.Context
import com.android.billingclient.api.Purchase
import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Purchases
import com.wolo.a222.feature.common.entity.SkuDeck
import com.wolo.a222.feature.common.model.repository.FB
import com.wolo.a222.market.Billing
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerFeature
class SplashScreenInteractorImpl
@Inject
constructor(
    private val fireBase: FB,
    private val billing: Billing,
    private val context: Context) : SplashScreenInteractor {

    override fun loadPacks(): Flowable<List<Pack>> =
        fireBase.getPacks().toFlowable()
            .subscribeOn(Schedulers.newThread())
            .map { it ->
                val listPacks = mutableListOf<Pack>()
                it.map { s ->
                    var name = ""
                    var cards = mutableListOf<String>()
                    var paid = false
                    var id = ""
                    var activeImage = ""
                    var nonActiveImage = ""
                    var priority = 0L
                    var alwaysActive = false
                    val keys = s.data?.keys
                    for (i in keys!!) {
                        when (i) {
                            "name" -> name = s.data?.get(i) as String
                            "cards" -> cards = s.data?.get(i) as MutableList<String>
                            "paid" -> paid = s.data?.get(i) as Boolean
                            "id" -> id = s.data?.get(i) as String
                            "activeImage" -> activeImage = s.data?.get(i) as String
                            "nonActiveImage" -> nonActiveImage = s.data?.get(i).toString()
                            "priority" -> priority = s.data?.get(i) as Long
                            "alwaysActive" -> alwaysActive = s.data?.get(i) as Boolean
                        }
                    }
                    listPacks.add(Pack(id, name, cards, paid, activeImage, nonActiveImage, priority, cards.size, alwaysActive))
                }
                listPacks.toList()
            }

    override fun loadSku(idList: List<String>): Flowable<List<SkuDeck>> =
        billing.getSkuInfo(context, idList)

    override fun loadPurchases(): Flowable<List<Purchases>> =
        billing.getPurchase(context)

}


