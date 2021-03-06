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
import com.wolo.a222.utils.CommonUtils
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ShopPresenterImpl @Inject constructor(
        private val navigator: Navigator,
        private val shopInteractor: ShopInteractor,
        private val commonUtils: CommonUtils
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
        val sysLang = commonUtils.getLanguage()
        Flowable.combineLatest(
                shopInteractor.getPacks(),
                shopInteractor.getPurchase(),
                shopInteractor.getSkuInfo(),
                Function3 { listPack: List<Pack>, purchases: List<Purchases>, skuList:List<SkuDeck> ->
                    val newList = listPack.filter { it.paid }.sortedBy { it.priority }.reversed()
                    var list = listOf<ShopVM>()
                    if (purchases.isNotEmpty()) {
                         list = newList.map { pack ->
                             val isBought = purchases.find { it.id == pack.id } != null
                             setPacksDependsOnLanguage(sysLang, pack, isBought)
                             //ShopVM(pack.id, pack.name, pack.activeImage, pack.nonActiveImage, isBought)
                            }
                    }
                    val listWithPrice = list.map {shopVM ->
                        skuList.find { it.skuType == shopVM.id}.let {
                            if (it != null){
                                shopVM.copy(price = it.price)}
                            else {
                                shopVM.copy()}
                        }
                    }
                    listWithPrice})
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

    private fun setPacksDependsOnLanguage(sysLang: String, pack: Pack, isBought: Boolean): ShopVM {
        val packName: String = if (sysLang == Const.LANG_EN) {
            pack.enName
        } else {
            pack.name
        }
        return ShopVM(pack.id, packName, pack.activeImage, pack.nonActiveImage, isBought)
    }
}