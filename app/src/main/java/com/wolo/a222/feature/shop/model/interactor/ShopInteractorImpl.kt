package com.wolo.a222.feature.shop.model.interactor

import android.content.Context
import com.wolo.a222.Market.Billing
import com.wolo.a222.feature.common.di.Scope.PerFeature
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerFeature
class ShopInteractorImpl @Inject constructor(
        private val context: Context,
        private val billing: Billing
) : ShopInteractor{

    override fun setPurchase(): Completable {
       return billing.queryPurchases(context)
                .subscribeOn(Schedulers.io())

    }
}