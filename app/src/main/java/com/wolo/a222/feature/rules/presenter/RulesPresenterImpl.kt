package com.wolo.a222.feature.rules.presenter

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
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RulesPresenterImpl @Inject constructor(
    private val navigator: Navigator
) : BasePresenter<RulesView>, RulesPresenter {

    private val compositeDisposable = CompositeDisposable()
    private val rulesSubject = BehaviorRelay.createDefault(RulesState(false))

    private var state: RulesState
        set(value) = rulesSubject.accept(value)
        get() = rulesSubject.value!!

    override fun onFinish() {
        compositeDisposable.clear()
    }

    override fun viewState(): Flowable<RulesState> {
        return rulesSubject.toFlowable(BackpressureStrategy.LATEST)
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

    }

    override fun closeRules() {
        navigator.closeRules()
    }
}