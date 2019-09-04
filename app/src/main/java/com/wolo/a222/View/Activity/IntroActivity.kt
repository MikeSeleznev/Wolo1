package com.wolo.a222.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.wolo.a222.Presenter.IntroActivityPresenter
import com.wolo.a222.R
import com.wolo.a222.Storage.Sku.SkuDB


class IntroActivity : AppCompatActivity() {

    private lateinit var loadingText: TextView
    private lateinit var introPresenter: IntroActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        init()
        loadingText = findViewById(R.id.loadingText)

    }

    override fun onStart() {
        super.onStart()

        //var db = Room.databaseBuilder(this, SkuDataBase::class.java, "skudatabase").allowMainThreadQueries().build()
        //var SkuDB = SkuDataBase.getInstance(this)

        /*var skuDB = SkuDB()
        skuDB.id = 2
        skuDB.name = "test"
        db.skuDao().insert(skuDB)*/
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
        loadingText.text = str
    }
}
