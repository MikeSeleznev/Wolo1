package com.wolo.a222.feature.common.billing

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.android.billingclient.api.*
import com.wolo.a222.Const
import com.wolo.a222.feature.common.entity.Purchases
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

class Billing : PurchasesUpdatedListener, BillingClientStateListener {

    private lateinit var billingClient: BillingClient
    private lateinit var params: SkuDetailsParams
    private lateinit var sportSku: SkuDetails
    private lateinit var eroticSku: SkuDetails
    private lateinit var ohFuckSku: SkuDetails
    private lateinit var alldecksSKU: SkuDetails
    var sku: SkuDetails? = null
    private lateinit var byingPack: String
    var skuReady: Boolean = false
    private lateinit var b: Unit
    private lateinit var activity: Activity


    fun getSkuInfo(context: Context, idList: List<String>) = Flowable
            .create<List<SkuDetails>>({ emitter ->

        billingClient = BillingClient.newBuilder(context)
                .enablePendingPurchases()
                .setListener(this)
                .build()


        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {

            }

            override fun onBillingSetupFinished(billingResult: BillingResult?) {

                params = SkuDetailsParams
                        .newBuilder()
                        .setSkusList(idList)
                        .setType(BillingClient.SkuType.INAPP)
                        .build()


                billingClient.querySkuDetailsAsync(params) { billingResult, skuDetailsList ->

                    if (skuDetailsList != null) {
                        emitter.onNext(skuDetailsList)
                    }
                    emitter.onComplete()
                }
            }
        })
    }, BackpressureStrategy.BUFFER)

    override fun onPurchasesUpdated(billingResult: BillingResult?, purchases: MutableList<Purchase>?) {
        if (billingResult?.responseCode == 0 && purchases != null) {

            for (purchase in purchases) {
                val acknowledgePurchaseResponseListener: AcknowledgePurchaseResponseListener = AcknowledgePurchaseResponseListener {

                }
                //purchase.purchaseState == Purchase.PurchaseState.PURCHASED
                if (!purchase.isAcknowledged) {
                    val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                            .setPurchaseToken(purchase.purchaseToken)
                            .build()
                    billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener)
                }
            }
        }
    }


    fun startConnection(activity: Activity, pack: String) {
        //var sku: SkuDetails? = null
        if (skuReady) {
            byingPack = pack
            if (pack == Const.SPORT) {
                sku = sportSku
            } else if (pack == Const.EROTIC) {
                sku = eroticSku
            } else if (pack == Const.OHFUCK) {
                sku = ohFuckSku
            } else if (pack == Const.ALLDECK) {
                sku = alldecksSKU
            }

            if (sku != null) {
                val flowParams = BillingFlowParams.newBuilder()
                        .setSkuDetails(sku)
                        .build()
                val responseCode = billingClient.launchBillingFlow(activity, flowParams)
            }
        }
    }

    fun getPurchases(context: Context) = Flowable.create<List<Purchase>>({ emitter ->

        billingClient = BillingClient.newBuilder(context)
                .enablePendingPurchases()
                .setListener(this)
                .build()
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {

            }

            override fun onBillingSetupFinished(billingResult: BillingResult?) {
                val purchasesResult: Purchase.PurchasesResult = billingClient.queryPurchases(BillingClient.SkuType.INAPP)
                if (billingResult != null) {
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        emitter.onNext(purchasesResult.purchasesList)

                    }
                } else {
                    //emitter.onError()
                }
                emitter.onComplete()
            }
        })
    }, BackpressureStrategy.BUFFER)

    fun buyDeck(i: SkuDetails, context: Context, act: Activity){

        sku = i
        activity = act

       b = BillingClient.newBuilder(context)
                .enablePendingPurchases()
                .setListener(this)
                .build()
                .startConnection(this)

    }

    override fun onBillingServiceDisconnected() {

    }

    override fun onBillingSetupFinished(billingResult: BillingResult?) {

        val flowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(sku)
                .build()

        val responseCode = billingClient.launchBillingFlow(activity, flowParams)
        if (responseCode.responseCode == 7){
            Toast.makeText(activity,responseCode.debugMessage , Toast.LENGTH_LONG).show()
        }
    }

    fun getPurchase(context: Context): Flowable<List<Purchases>>  = Flowable.create<List<Purchases>>({ emitter ->
            billingClient = BillingClient.newBuilder(context)
                    .enablePendingPurchases()
                    .setListener(this)
                    .build()
            billingClient.startConnection(object : BillingClientStateListener {
                override fun onBillingServiceDisconnected() {

                }

                override fun onBillingSetupFinished(billingResult: BillingResult?) {
                    val purchasesResult: Purchase.PurchasesResult = billingClient.queryPurchases(BillingClient.SkuType.INAPP)
                    if (billingResult != null) {
                        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                            val list = purchasesResult.purchasesList
                                    .map { Purchases(it.sku) }
                            emitter.onNext(list)

                        }
                    } else {
                        //emitter.onError()
                    }
                    emitter.onComplete()
                }
            })
        }, BackpressureStrategy.BUFFER)


}

