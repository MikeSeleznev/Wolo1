package com.wolo.a222.Presenter

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import com.wolo.a222.Const
import com.wolo.a222.Model.Firebase.FB
import com.wolo.a222.Model.Firebase.InitFB
import com.wolo.a222.Model.Firebase.Packs
import com.wolo.a222.R
import com.wolo.a222.Staff.SaveLoadDataJson
import com.wolo.a222.Storage.Packs.PacksDB
import com.wolo.a222.View.Activity.IntroActivity
import com.wolo.a222.View.Activity.MainActivity
import com.wolo.a222.di.App

class IntroActivityPresenter {

    private var view: IntroActivity? = null

    fun attachView(introActivity: IntroActivity){
        view = introActivity
    }

    fun detachView(){
        view = null
    }


    fun getPacks(){
      FB().flowableFB()
                .map { it ->  it.getValue(Packs::class.java)!!}
                .doOnNext {
                    view?.setLoadingText("Загрузка карт")
                    //var packsDB = PacksDB()
                    //packsDB.name = "cards"
                    //packsDB.packs = setOf<String>(it.erotic.toSet())
                    //App.getComponent().packsDatabase().packsDao().insert(packsDB)
                     }
                .subscribe()

    }
}