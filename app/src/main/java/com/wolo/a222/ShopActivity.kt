package com.wolo.a222


import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.SkuDetails
import com.android.billingclient.api.SkuDetailsParams
import com.wolo.a222.Market.Billing
import com.wolo.a222.Market.ButtonOnClick
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

import java.util.ArrayList


class ShopActivity : AppCompatActivity() {

    private lateinit var closeMenuImageButton: ImageButton
    internal lateinit var frameLayoutLoading: FrameLayout
    private lateinit var imageButtonSport: ImageButton
    internal lateinit var loadingText2: TextView
    private lateinit var imageButtonOHFUCK: ImageButton
    private lateinit var imageButtonEROTIC: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop)

        var observableBilling: Billing = Billing();

        closeMenuImageButton = findViewById<View>(R.id.closeMenuImageButtonShopActivity) as ImageButton
        closeMenuImageButton.setOnClickListener { finish() }
        imageButtonSport = findViewById(R.id.SPORT)
        imageButtonSport.setOnClickListener(ButtonOnClick(imageButtonSport, this, observableBilling, this@ShopActivity, Const.SPORT))

        imageButtonOHFUCK = findViewById(R.id.OHFUCK)
        imageButtonOHFUCK.setOnClickListener(ButtonOnClick(imageButtonOHFUCK, this, observableBilling, this@ShopActivity, Const.OHFUCK))

        imageButtonEROTIC = findViewById(R.id.EROTIC)
        imageButtonEROTIC.setOnClickListener(ButtonOnClick(imageButtonEROTIC, this, observableBilling, this@ShopActivity, Const.EROTIC))

        frameLayoutLoading = findViewById(R.id.frameLayoutLoading)
        loadingText2 = findViewById(R.id.loadingText2)

        //market
        val observerBilling: Observer<String> = object: Observer<String> {
            override fun onComplete() {

                var handler: Handler = Handler()
                handler.postDelayed(Runnable {
                    frameLayoutLoading.visibility = View.INVISIBLE
                    loadingText2.visibility = View.INVISIBLE }, 2000)

            }

            override fun onSubscribe(d: Disposable) {
                val a = "a"

            }

            override fun onNext(t: String) {
                val a = "a"

            }

            override fun onError(e: Throwable) {

            }

        }


        observableBilling.createBilling(this).subscribe(observerBilling)

    }

}