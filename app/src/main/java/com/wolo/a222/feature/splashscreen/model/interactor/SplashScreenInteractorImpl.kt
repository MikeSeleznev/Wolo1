package com.wolo.a222.feature.auth.model.interactor


import android.content.Context
import com.wolo.a222.Market.Billing
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.model.repository.FB
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerFeature
class SplashScreenInteractorImpl
@Inject
constructor(
        private val fB: FB,
        private val billing: Billing,
        private val context: Context) : SplashScreenInteractor {

    override fun loadPacks(): Completable {
        return fB.getPacks()
                .subscribeOn(Schedulers.io())
                .map {it ->
                    var listPacks = mutableListOf<Pack>()
                    it.map { s->
                        var name = ""
                        var cards = emptyList<String>()
                        var paid = false
                        var id = ""
                        var keys = s.data?.keys
                        for (i in keys!!){
                            when (i){
                                "name" -> name = s.data?.get(i) as String
                                "cards" -> cards = s.data?.get(i) as List<String>
                                "paid" -> paid = s.data?.get(i) as Boolean
                                "id" -> id = s.data?.get(i) as String
                            }
                        }
                        listPacks.add(Pack(id, name, cards,paid))
                        game.packs = listPacks.toTypedArray()
                    }
                }
               /* .flatMap {
                    billing.createBilling(context)
                }*/
                .flatMapCompletable {
                    Completable.complete() }
    }
}


/*
return fB.getPacks()
.subscribeOn(Schedulers.io())
.flatMap {
    billing.createBilling().first()
}
.flatMapCompletable {
    fB.addCardsToGame(it)
    Completable.complete() }
*/
