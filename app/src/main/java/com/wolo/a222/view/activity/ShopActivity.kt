package com.wolo.a222.view.activity


import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.billingclient.api.*
import com.wolo.a222.R
import com.wolo.a222.WoloApp
import com.wolo.a222.feature.common.view.MainActivity
import com.wolo.a222.feature.shop.presenter.ShopPresenter
import com.wolo.a222.feature.shop.presenter.ShopState
import com.wolo.a222.feature.shop.view.adapter.DataAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_shop.*
import javax.inject.Inject


class ShopActivity : AppCompatActivity(), BillingClientStateListener {

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
    private lateinit var billingClient: BillingClient
    var sku: SkuDetails? = null

    companion object {
        fun newInstance() = ShopActivity()
    }
    @Inject
    lateinit var presenter: ShopPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.shop)
        setContentView(R.layout.fragment_shop)


        presenter.viewState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleState)


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

    private fun handleState(state: ShopState) {

        grid_view.adapter = DataAdapter(this, state.skuDeck)

        grid_view.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            buyDeck(i)
        }
    }

    fun buyDeck(i: Int){

        sku = WoloApp.game.skuDetailsList[i]

        //billingClient = BillingClient.newBuilder(context).build()

        billingClient.startConnection(this)

    }

    override fun onBillingServiceDisconnected() {

    }

    override fun onBillingSetupFinished(billingResult: BillingResult?) {

        val flowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(sku)
                .build()

        val responseCode = billingClient.launchBillingFlow(MainActivity.newInstance(), flowParams)
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

      /*  observableBilling.getSkuInfo(this)
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
