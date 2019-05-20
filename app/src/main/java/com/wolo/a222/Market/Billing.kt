package com.wolo.a222.Market

 import android.app.Activity
 import android.content.Context
 import android.content.SharedPreferences
 import android.preference.PreferenceManager
 import com.android.billingclient.*
 import com.android.billingclient.api.*
 import com.google.gson.Gson
 import com.wolo.a222.Const
 import com.wolo.a222.Game
 import com.wolo.a222.ShopActivity
 import io.reactivex.Flowable
 import io.reactivex.FlowableOnSubscribe
 import io.reactivex.Observable
 import io.reactivex.ObservableOnSubscribe

class Billing(): PurchasesUpdatedListener {

    private lateinit var billingClient: BillingClient
    private lateinit var params: SkuDetailsParams
    private lateinit var sportSku: SkuDetails
    private lateinit var eroticSku: SkuDetails
    private lateinit var ohFuckSku: SkuDetails
    private lateinit var mcontext: Context
    var sku: SkuDetails? = null
    private lateinit var byingPack: String
    private lateinit var game: Game
    private lateinit var sPref: SharedPreferences


    fun createBilling(context: Context): Observable<String> {

        mcontext = context

        val billingObservable: Observable<String> = Observable.create(ObservableOnSubscribe { emitter ->
            billingClient = BillingClient.newBuilder(context)
                    .enablePendingPurchases()
                    .setListener(this)
                    .build()

            billingClient.startConnection(object : BillingClientStateListener {
                override fun onBillingServiceDisconnected() {

                }

                override fun onBillingSetupFinished(billingResult: BillingResult?) {
                    emitter.onNext("Ok")
                    emitter.onComplete()

                    val skuList = listOf("000003", "000004", "000005", "000006")
                    params = SkuDetailsParams
                            .newBuilder()
                            .setSkusList(skuList)
                            .setType(BillingClient.SkuType.INAPP)
                            .build()


                    billingClient.querySkuDetailsAsync(params) { billingResult, skuDetailsList ->
                        for (skuDetails in skuDetailsList) {
                            val sku = skuDetails.sku
                            val price = skuDetails.price
                            if (Const.sportSKU == sku) {
                                sportSku = skuDetails
                            } else if (Const.eroticSKU == sku) {
                                eroticSku = skuDetails
                            } else if (Const.ohfuckSKU == sku) {
                                ohFuckSku = skuDetails
                            }
                        }
                    }
                }
            })

        })

        return billingObservable
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
                    game.setPaidOhfuck()
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
        byingPack = pack
        if (pack == Const.SPORT) {
            sku = sportSku
        } else if (pack == Const.EROTIC) {
            sku = eroticSku
        } else if (pack == Const.OHFUCK) {
            sku = ohFuckSku
        }

        if (sku != null) {
            val flowParams = BillingFlowParams.newBuilder()
                    .setSkuDetails(sku)
                    .build()
            val responseCode = billingClient.launchBillingFlow(activity, flowParams)
        }
    }

    fun queryPurchases(context: Context) {
        /*val flowablePurchases: Observable<String> = Observable.create(ObservableOnSubscribe  {emitter ->
            val purchasesResult: Purchase.PurchasesResult = billingClient.queryPurchases(BillingClient.SkuType.INAPP)
                    emitter.onNext("Ok")
                    emitter.onComplete()
        })
        return flowablePurchases*/

        billingClient = BillingClient.newBuilder(context)
                .enablePendingPurchases()
                .setListener(this)
                .build()
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {

            }

            override fun onBillingSetupFinished(billingResult: BillingResult?) {
                val purchasesResult: Purchase.PurchasesResult = billingClient.queryPurchases(BillingClient.SkuType.INAPP)
                val gson = Gson()
                val json = PreferenceManager.getDefaultSharedPreferences(context).getString("game", "")
                game = gson.fromJson(json, Game::class.java)
                for (purchase in purchasesResult.purchasesList) {
                    if (purchase.sku == Const.sportSKU) {
                        game.setPaidSport()
                    } else if (purchase.sku == Const.eroticSKU) {
                        game.setPaidErotic()
                    } else if (purchase.sku == Const.ohfuckSKU) {
                        game.setPaidOhfuck()
                    } else if (purchase.sku == Const.alldecksSKU) {
                        game.setPaidSport()
                        game.setPaidErotic()
                        game.setPaidOhfuck()
                    }
                }
                val jsonT = gson.toJson(game)
                sPref = PreferenceManager.getDefaultSharedPreferences(context)
                val ed = sPref.edit()
                ed.putString("game", jsonT)
                ed.commit()
            }
        })


    }
}

