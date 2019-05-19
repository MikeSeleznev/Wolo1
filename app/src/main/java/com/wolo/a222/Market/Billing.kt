package com.wolo.a222.Market

 import android.app.Activity
import android.content.Context
 import android.preference.PreferenceManager
 import com.android.billingclient.*
import com.android.billingclient.api.*
 import com.google.gson.Gson
 import com.wolo.a222.Const
 import com.wolo.a222.Game
 import com.wolo.a222.ShopActivity
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
                    params  = SkuDetailsParams
                            .newBuilder()
                            .setSkusList(skuList)
                            .setType(BillingClient.SkuType.INAPP)
                            .build()


                    billingClient.querySkuDetailsAsync(params) { billingResult, skuDetailsList ->
                        for (skuDetails in skuDetailsList) {
                            val sku = skuDetails.sku
                            val price = skuDetails.price
                            if ("000003" == sku) {
                                sportSku = skuDetails
                            } else if ("000005" == sku) {
                                eroticSku = skuDetails
                            }
                            else if ("000006" == sku) {
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
        if (billingResult?.responseCode != 0){
            val gson = Gson()
            val json = PreferenceManager.getDefaultSharedPreferences(mcontext).getString("game", "")
            var  game = gson.fromJson(json, Game::class.java)

            if (byingPack == Const.SPORT){
                game.setPaidSport()
            } else if (byingPack == Const.EROTIC){
                game.setPaidErotic()
            } else if (byingPack == Const.OHFUCK){
                game.setPaidOhfuck()
            }

            var jsonT = gson.toJson(game)
            val sPref = PreferenceManager.getDefaultSharedPreferences(mcontext)
            val ed = sPref.edit()
            ed.putString("game", jsonT)
            ed.commit()
        }
    }


    fun startConnection(activity:Activity, pack: String){
        //var sku: SkuDetails? = null
        byingPack = pack
        if (pack == Const.SPORT){
            sku = sportSku
        } else if (pack == Const.EROTIC){
            sku = eroticSku
        } else if (pack == Const.OHFUCK){
            sku = ohFuckSku
        }

        if (sku != null){
        val flowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(sku)
                .build()
        val responseCode = billingClient.launchBillingFlow(activity , flowParams)}
    }



}