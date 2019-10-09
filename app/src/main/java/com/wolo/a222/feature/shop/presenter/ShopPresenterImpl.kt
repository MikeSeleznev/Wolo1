package com.wolo.a222.feature.shop.presenter

import android.annotation.SuppressLint
import android.app.Activity
import com.android.billingclient.api.Purchase
import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.Const
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.navigation.Navigator
import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.shop.model.interactor.ShopInteractor
import com.wolo.a222.model.sku.SkuDeck
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ShopPresenterImpl @Inject constructor(
        private val navigator: Navigator,
        private val shopInteractor: ShopInteractor
) : BasePresenter<ShopView>, ShopPresenter {

    private val compositeDisposable = CompositeDisposable()
    private val taskSubject = BehaviorRelay.createDefault(ShopState(false))

    private var state: ShopState
        set(value) = taskSubject.accept(value)
        get() = taskSubject.value!!

    override fun initState() {
        state = ShopState(false)
    }

    override fun onFinish() {
        compositeDisposable.clear()
    }


    override fun viewState(): Flowable<ShopState> {
        return taskSubject.toFlowable(BackpressureStrategy.LATEST)
                .onBackpressureBuffer(3)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    if (compositeDisposable.size() == 0) {
                        initialLoadSettings()
                    }
                }
    }

    @SuppressLint("CheckResult")
    private fun initialLoadSettings() {
        Flowable.combineLatest(
                shopInteractor.getSkuInfo(),
                shopInteractor.getPurchase(),
                BiFunction { listSku: List<SkuDeck>, purchases: MutableList<Purchase> ->
                    if (purchases.size > 0){
                    listSku.map { skuDeck ->
                        val a = purchases.find { it.sku == skuDeck.skuType }
                        if (a != null) {
                            skuDeck.copy(isBought = true)
                        } else {
                            skuDeck.copy()
                        }
                    }
                }else {
                        listSku
                    }
                }
        )
                .map { it ->
                    val packs = game.packs
                    it.map { skuDeck ->
                        val a = packs.find { it.id == skuDeck.skuType }
                        if (a != null) {
                            skuDeck.copy(name = a.name, activeImage = a.activeImage, nonActiveImage = a.nonActiveImage)
                        } else skuDeck.copy()
                    }
                }
                .onBackpressureBuffer(3)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    setViewState(it)
                }.also {
                    compositeDisposable.add(it)
                }
    }

    override fun closeShop() {
        navigator.closeShop()
    }

    private fun setViewState(skuDeck: List<SkuDeck>){
        val allDecksSKU = skuDeck.find { it.skuType == Const.alldecksSKU}
        state = if (allDecksSKU != null && allDecksSKU.isBought){
            val sku = skuDeck.map { it.copy(isBought = true) }
            state.copy(skuDeck = sku)
        } else{
            state.copy(skuDeck = skuDeck)
        }
    }

    override fun buyDeck(i: Int) {
        shopInteractor.buyDeck(i)
    }
}