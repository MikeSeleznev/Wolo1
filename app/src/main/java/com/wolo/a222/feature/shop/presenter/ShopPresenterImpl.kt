package com.wolo.a222.feature.shop.presenter

import android.annotation.SuppressLint
import android.app.Activity
import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.Const
import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Purchases
import com.wolo.a222.feature.common.entity.SkuDeck
import com.wolo.a222.feature.common.navigation.Navigator
import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.shop.model.interactor.ShopInteractor
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
                shopInteractor.getPacks(),
                shopInteractor.getPurchase(),
                BiFunction { listPack: List<Pack>, purchases: List<Purchases> ->
                    val newList = listPack.filter { it.paid }
                    var list = listOf<ShopVM>()
                    if (purchases.isNotEmpty()) {
                         list = newList.map { pack ->
                            purchases.find { it.id == pack.id }
                                    .let {
                                        if (it != null) ShopVM(pack.id, pack.name, pack.activeImage, pack.nonActiveImage, true)
                                        else ShopVM(pack.id, pack.name, pack.activeImage, pack.nonActiveImage, false)
                                    }
                            }
                    }
                    list})
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

    private fun setViewState(shopVMList: List<ShopVM>) {
        val allDecksSKU = shopVMList.find { it.id == Const.alldecksSKU }
        state = if (allDecksSKU != null && allDecksSKU.isBought){
            val sku = shopVMList.map {
                it.copy(isBought = true)
            }
            state.copy(listVM = sku)
        } else {
            state.copy(listVM = shopVMList)
        }
    }

    override fun buyDeck(i: ShopVM, act: Activity) {
        shopInteractor.buyDeck(i, act)
    }
}