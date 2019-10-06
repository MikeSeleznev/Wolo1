package com.wolo.a222.feature.splashscreen.model.interactor

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
        private val fB: FB) : SplashScreenInteractor {

    override fun loadPacks(): Completable {
        return fB.getPacks()
                .subscribeOn(Schedulers.io())
                .map {it ->
                    val listPacks = mutableListOf<Pack>()
                    it.map { s->
                        var name = ""
                        var cards = emptyList<String>()
                        var paid = false
                        var id = ""
                        var activeImage = ""
                        var nonActiveImage = ""
                        val keys = s.data?.keys
                        for (i in keys!!){
                            when (i){
                                "name" -> name = s.data?.get(i) as String
                                "cards" -> cards = s.data?.get(i) as List<String>
                                "paid" -> paid = s.data?.get(i) as Boolean
                                "id" -> id = s.data?.get(i) as String
                                "activeImage" -> activeImage = s.data?.get(i) as String
                                "nonActiveImage" -> nonActiveImage = s.data?.get(i).toString()
                            }
                        }
                        listPacks.add(Pack(id, name, cards,paid, activeImage, nonActiveImage))
                    }
                    game.packs = listPacks
                }
                .flatMapCompletable {
                    Completable.complete() }
    }
}


