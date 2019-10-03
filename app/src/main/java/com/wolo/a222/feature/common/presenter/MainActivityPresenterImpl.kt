package com.wolo.a222.feature.common.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.feature.common.navigation.Navigator
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainActivityPresenterImpl
@Inject
constructor(
        val navigator: Navigator
)
    : BasePresenter<MainActivityView>, MainActivityPresenter {

    companion object {
        private val INITIAL_STATE = MainActivityState(closeMenuIsVisible = true, topMenuIsVisible = false)
    }

    private val compositeDisposable = CompositeDisposable()

    private val registrationSubject = BehaviorRelay.createDefault(INITIAL_STATE)

    override fun viewState(): Flowable<MainActivityState> {
        return registrationSubject.toFlowable(BackpressureStrategy.BUFFER)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())
    }

    override fun onFinish() {
        compositeDisposable.clear()
    }

}