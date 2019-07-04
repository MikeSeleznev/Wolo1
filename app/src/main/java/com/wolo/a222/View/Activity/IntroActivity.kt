package com.wolo.a222.View.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
//import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.wolo.a222.Presenter.FirebasePresenter
import com.wolo.a222.Presenter.IntroActivityPresenter
import com.wolo.a222.R
import com.wolo.a222.Storage.Sku.SkuDB


class IntroActivity : AppCompatActivity() {

    private lateinit var loadingText: TextView
    private lateinit var presenter: FirebasePresenter
    private lateinit var introPresenter: IntroActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        init()
        //val db = FirebaseFirestore.getInstance().document("packs")
        loadingText = findViewById(R.id.loadingText)
        presenter = FirebasePresenter()

        var skuDB = SkuDB()
        skuDB.id = 2
        skuDB.name = "test"

        //App.getComponent().skuDataBase().skuDao().insert(skuDB)
    }

    override fun onStart() {
        super.onStart()
        presenter.bindView(this, this)
        presenter.init(this)
        presenter.startGame()
        var connection = presenter.hasConnection(this)
        //var db = Room.databaseBuilder(this, SkuDataBase::class.java, "skudatabase").allowMainThreadQueries().build()
        //var SkuDB = SkuDataBase.getInstance(this)

        /*var skuDB = SkuDB()
        skuDB.id = 2
        skuDB.name = "test"
        db.skuDao().insert(skuDB)*/
    }

    override fun onStop() {
        super.onStop()
        //presenter.saveData()
        presenter.unbindView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        introPresenter.detachView()
    }

    fun init(){
        introPresenter = IntroActivityPresenter()
        introPresenter.attachView(this)
        introPresenter.getPacks()
    }

    fun setLoadingText(str: String){

    }
}
