package com.wolo.a222.View.Activity


import android.os.Bundle
import android.view.View
import android.widget.CheckedTextView
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wolo.a222.R


class ShopActivity : AppCompatActivity() {

    private lateinit var closeMenuImageButton: ImageButton
    internal lateinit var frameLayoutLoading: FrameLayout
    private lateinit var imageButtonSport: ImageButton
    internal lateinit var loadingText2: TextView
    private lateinit var imageButtonOHFUCK: ImageButton
    private lateinit var imageButtonEROTIC: ImageButton
    private lateinit var imageButtonAlldeck: ImageButton
    private lateinit var kolodanumcards3: CheckedTextView//Sport
    private lateinit var kolodanumcards5: CheckedTextView//Erotic
    private lateinit var kolodanumcards6: CheckedTextView//OhFuck
    private lateinit var kolodanumcards7: CheckedTextView//AllDeck

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
    }
}   /*    val gson = Gson()
           val json = PreferenceManager.getDefaultSharedPreferences(this).getString("game", "")
           var  game = gson.fromJson(json, Game::class.java)

           var observableBilling: Billing = Billing();

           if (game.getPaidErotic() == true){
               imageButtonEROTIC.setImageResource(R.drawable.eroticclose)
           } else {
               imageButtonEROTIC.setOnClickListener(ButtonOnClick(imageButtonEROTIC, this, observableBilling, this@ShopActivity, Const.EROTIC))
           }

           if (game.getPaidOhFuck() == true){
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

           kolodanumcards3 = findViewById(R.id.numCardsSport)
           kolodanumcards5 = findViewById(R.id.numCardsErotic)
           kolodanumcards6 = findViewById(R.id.numCardsOhFuck)
           kolodanumcards7 = findViewById(R.id.kolodanumcards7)*/
        //market

      /*  observableBilling.createBilling(this)
                //.observeOn(Schedulers.io())
                .doOnError {  }
                .doOnNext {
                    var listButtons = mutableMapOf<String, View>()
                    listButtons.put("000003", kolodanumcards3)//Sport
                    listButtons.put("000005", kolodanumcards5)//Erotic
                    listButtons.put("000006", kolodanumcards6)//OhFuck
                    listButtons.put("000007", kolodanumcards7)//AllDeck

                    for (item in listButtons){
                        for (s in it){
                            if (s.skuType == item.key){
                                (item.value as CheckedTextView).text = s.price
                            }
                        }
                    }
                }
                .doOnComplete {
                    var handler: Handler = Handler(Looper.getMainLooper())
                    handler.postDelayed(Runnable {
                        frameLayoutLoading.visibility = View.INVISIBLE
                        loadingText2.visibility = View.INVISIBLE }, 2000)
                }
                .subscribe()

    }*/
