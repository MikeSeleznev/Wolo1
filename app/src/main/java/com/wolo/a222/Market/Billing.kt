package com.wolo.a222.Market

 import android.app.Activity
 import android.content.Context
 import android.preference.PreferenceManager
 import com.android.billingclient.api.*
 import com.google.gson.Gson
 import com.wolo.a222.Const
 import com.wolo.a222.Model.SKU.SkuDeck
 import com.wolo.a222.WoloApp.Companion.game
 import com.wolo.a222.feature.common.model.Game
 import io.reactivex.Completable
 import io.reactivex.Single
 import io.reactivex.SingleEmitter

class Billing : PurchasesUpdatedListener {

    private lateinit var billingClient: BillingClient
    private lateinit var params: SkuDetailsParams
    private lateinit var sportSku: SkuDetails
    private lateinit var eroticSku: SkuDetails
    private lateinit var ohFuckSku: SkuDetails
    private lateinit var alldecksSKU: SkuDetails
    private lateinit var mcontext: Context
    var sku: SkuDetails? = null
    private lateinit var byingPack: String
    var skuReady: Boolean = false


    fun createBilling(context: Context): Single<List<SkuDeck>> = Single.create{emitter: SingleEmitter<List<SkuDeck>> ->

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
                            .setSkusList(listOf("000003", "000007", "000005", "000006"))
                            .setType(BillingClient.SkuType.INAPP)
                            .build()


                    billingClient.querySkuDetailsAsync(params) { billingResult, skuDetailsList ->

                        if (skuDetailsList!= null){
                            val skuList: List<SkuDeck> = skuDetailsList.map {
                                SkuDeck(it.sku, it.title, it.price, "")
                            }
                            skuReady = true
                       for (skuDetails in skuDetailsList) {
                            //skuList.add(Deck())

                            val sku = skuDetails.sku
                            val price = skuDetails.price
                            if (Const.sportSKU == sku) {
                                sportSku = skuDetails
                            } else if (Const.eroticSKU == sku) {
                                eroticSku = skuDetails
                            } else if (Const.ohfuckSKU == sku) {
                                ohFuckSku = skuDetails
                            } else if (Const.alldecksSKU == sku) {
                                alldecksSKU = skuDetails
                            }

                        }
                            emitter.onSuccess(skuList)
                        }


                    }
                }
            })
    }

    override fun onPurchasesUpdated(billingResult: BillingResult?, purchases: MutableList<Purchase>?) {
        if (billingResult?.responseCode == 0 && purchases != null) {
            val gson = Gson()
            val json = PreferenceManager.getDefaultSharedPreferences(mcontext).getString("game", "")
            var game = gson.fromJson(json, Game::class.java)

            for (purchase in purchases) {
                val acknowledgePurchaseResponseListener: AcknowledgePurchaseResponseListener = AcknowledgePurchaseResponseListener {

                }
                purchase.purchaseState === Purchase.PurchaseState.PURCHASED
                if (!purchase.isAcknowledged) {
                    val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                            .setPurchaseToken(purchase.purchaseToken)
                            .build()
                    billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener)
                }

                if (byingPack == Const.SPORT) {
                    game.setPaidSport()
                } else if (byingPack == Const.EROTIC) {
                    game.setPaidErotic()
                } else if (byingPack == Const.OHFUCK) {
                    game.setPaidOhFuck()
                } else if (byingPack == Const.ALLDECK) {
                    game.setPaidAllDecks()
                }

            }

            var jsonT = gson.toJson(game)
            val sPref = PreferenceManager.getDefaultSharedPreferences(mcontext)
            val ed = sPref.edit()
            ed.putString("game", jsonT)
            ed.commit()
        }
    }


    fun startConnection(activity: Activity, pack: String) {
        //var sku: SkuDetails? = null
        if (skuReady){
        byingPack = pack
        if (pack == Const.SPORT) {
            sku = sportSku
        } else if (pack == Const.EROTIC) {
            sku = eroticSku
        } else if (pack == Const.OHFUCK) {
            sku = ohFuckSku
        } else if (pack == Const.ALLDECK) {
            sku = alldecksSKU }

        if (sku != null) {
            val flowParams = BillingFlowParams.newBuilder()
                    .setSkuDetails(sku)
                    .build()
            val responseCode = billingClient.launchBillingFlow(activity, flowParams)
        }
        }
    }

    fun queryPurchases(context: Context)  = Completable.create { completable ->

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
                        purchasesResult.purchasesList.forEach {
                            when (it.sku)  {
                                Const.sportSKU -> game.setPaidSport()
                                Const.eroticSKU -> game.setPaidErotic()
                                Const.ohfuckSKU -> game.setPaidOhFuck()
                                Const.alldecksSKU -> game.setPaidAllDecks()
                            }
                        }
                    }
                }
                completable.onComplete()
            }
        })
    }
}

