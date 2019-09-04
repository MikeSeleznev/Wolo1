package com.wolo.a222.feature.common.model.repository

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers



class WoloRepositoryImpl  {


    private val dataChangesBus = PublishRelay.create<Pair<String, Any>>()

    private fun setValueSPrefrence(key: String, value: String) = Completable.fromCallable {


        dataChangesBus.accept(key to value)
    }.subscribeOn(Schedulers.io())

}