package com.wolo.a222.feature.splashscreen.model.interactor

import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.SkuDeck
import io.reactivex.Flowable

interface SplashScreenInteractor {

    fun loadPacks(): Flowable<List<Pack>>

    fun loadSku(idList: List<String>): Flowable<List<SkuDeck>>

    //fun setPacks(packs: List<Pack>)
}