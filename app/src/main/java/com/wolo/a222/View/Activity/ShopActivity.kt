package com.wolo.a222.View.Activity


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView

import com.google.gson.Gson
import com.wolo.a222.Const
import com.wolo.a222.Game
import com.wolo.a222.Market.Billing
import com.wolo.a222.Market.ButtonOnClick
import com.wolo.a222.R
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


class ShopActivity : AppCompatActivity() {

    private lateinit var closeMenuImageButton: ImageButton
    internal lateinit var frameLayoutLoading: FrameLayout
    private lateinit var imageButtonSport: ImageButton
    internal lateinit var loadingText2: TextView
    private lateinit var imageButtonOHFUCK: ImageButton
    private lateinit var imageButtonEROTIC: ImageButton
    private lateinit var imageButtonAlldeck: ImageButton

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

        imageButtonAlldeck = findViewById(R.id.alldeck)
        imageButtonAlldeck.setOnClickListener(ButtonOnClick(imageButtonAlldeck, this, observableBilling, this@ShopActivity, Const.ALLDECK))

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

    @Override
    override fun onStart() {
        super.onStart()
        val gson = Gson()
        val json = PreferenceManager.getDefaultSharedPreferences(this).getString("game", "")
        var  game = gson.fromJson(json, Game::class.java)

        if (game.getPaidErotic() == true){
            imageButtonEROTIC.setImageResource(R.drawable.eroticclose)
        }
        if (game.getPaidOhfuck() == true){
            imageButtonOHFUCK.setImageResource(R.drawable.ohfuckclose)
        }
        if (game.getPaidSport() == true){
            imageButtonSport.setImageResource(R.drawable.sportclose)
        }
        if (game.getPaidAlldecks() == true){
            imageButtonAlldeck.setImageResource(R.drawable.alldecksclose)
        }
    }
}