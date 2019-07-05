package com.wolo.a222.Presenter


import com.wolo.a222.Model.Firebase.FB
import com.wolo.a222.View.Activity.IntroActivity
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
        FB().initFB().map {
            it -> it.data
                }
                .doOnNext {
                    view?.setLoadingText("Загрузка карт")
                    App.utilsDB.addPacksToDB(it)
                }
                .subscribe()
      /*FB().flowableFB()
                .map { it ->  it.getValue(Packs::class.java)!!}
                .doOnNext {
                    view?.setLoadingText("Загрузка карт")
                    //var packsDB = PacksDB()
                    //packsDB.name = "cards"
                    //packsDB.packs = setOf<String>(it.erotic.toSet())
                    //App.getComponent().packsDatabase().packsDao().insert(packsDB)
                     }
                .subscribe()*/

    }
}