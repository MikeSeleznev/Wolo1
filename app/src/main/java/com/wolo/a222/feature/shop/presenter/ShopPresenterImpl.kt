package com.wolo.a222.feature.shop.presenter

import android.content.Context
import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.Market.Billing
import com.wolo.a222.feature.shop.model.interactor.ShopInteractor
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ShopPresenterImpl @Inject constructor(
        private val context: Context,
        private val billing: Billing,
        private val shopInteractor: ShopInteractor
) : ShopPresenter {

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

    private fun initialLoadSettings() {

        shopInteractor.setPurchase()
                .doOnComplete {
                    var a = "a"
                }
                .subscribe()
    }
}