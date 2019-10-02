package com.wolo.a222.feature.shop.model.interactor

import io.reactivex.Completable

interface ShopInteractor{

    fun setPurchase(): Completable

}