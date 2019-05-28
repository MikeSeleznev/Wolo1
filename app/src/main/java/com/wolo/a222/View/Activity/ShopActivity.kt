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

        closeMenuImageButton = findViewById<View>(R.id.closeMenuImageButtonShopActivity) as ImageButton
        closeMenuImageButton.setOnClickListener { finish() }

        imageButtonSport = findViewById(R.id.SPORT)
        //imageButtonSport.setOnClickListener(ButtonOnClick(imageButtonSport, this, observableBilling, this@ShopActivity, Const.SPORT))

        imageButtonOHFUCK = findViewById(R.id.OHFUCK)
        //imageButtonOHFUCK.setOnClickListener(ButtonOnClick(imageButtonOHFUCK, this, observableBilling, this@ShopActivity, Const.OHFUCK))

        imageButtonEROTIC = findViewById(R.id.EROTIC)
        //imageButtonEROTIC.setOnClickListener(ButtonOnClick(imageButtonEROTIC, this, observableBilling, this@ShopActivity, Const.EROTIC))

        imageButtonAlldeck = findViewById(R.id.alldeck)
        //imageButtonAlldeck.setOnClickListener(ButtonOnClick(imageButtonAlldeck, this, observableBilling, this@ShopActivity, Const.ALLDECK))

        frameLayoutLoading = findViewById(R.id.frameLayoutLoading)
        loadingText2 = findViewById(R.id.loadingText2)



    }

    @Override
    override fun onStart() {
        super.onStart()
        val gson = Gson()
        val json = PreferenceManager.getDefaultSharedPreferences(this).getString("game", "")
        var  game = gson.fromJson(json, Game::class.java)

        var observableBilling: Billing = Billing();

        if (game.getPaidErotic() == true){
            imageButtonEROTIC.setImageResource(R.drawable.eroticclose)
        } else {
            imageButtonEROTIC.setOnClickListener(ButtonOnClick(imageButtonEROTIC, this, observableBilling, this@ShopActivity, Const.EROTIC))
        }

        if (game.getPaidOhfuck() == true){
            imageButtonOHFUCK.setImageResource(R.drawable.ohfuckclose)
        } else {
            imageButtonOHFUCK.setOnClickListener(ButtonOnClick(imageButtonOHFUCK, this, observableBilling, this@ShopActivity, Const.OHFUCK))
        }

        if (game.getPaidSport() == true){
            imageButtonSport.setImageResource(R.drawable.sportclose)
        } else {
            imageButtonSport.setOnClickListener(ButtonOnClick(imageButtonSport, this, observableBilling, this@ShopActivity, Const.SPORT))

        }
        if (game.getPaidAlldecks() == true){
            imageButtonAlldeck.setImageResource(R.drawable.alldecksclose)
            imageButtonSport.setImageResource(R.drawable.sportclose)
            imageButtonOHFUCK.setImageResource(R.drawable.ohfuckclose)
            imageButtonEROTIC.setImageResource(R.drawable.eroticclose)
        } else {
            imageButtonAlldeck.setOnClickListener(ButtonOnClick(imageButtonAlldeck, this, observableBilling, this@ShopActivity, Const.ALLDECK))
        }

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