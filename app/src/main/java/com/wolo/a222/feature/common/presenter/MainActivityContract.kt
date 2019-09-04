package com.wolo.a222.feature.common.presenter

import io.reactivex.Flowable


interface MainActivityPresenter: Presenter<MainActivityView> {
    fun viewState(): Flowable<MainActivityState>
}

interface MainActivityView: View

data class MainActivityState(
        val closeMenuIsVisible: Boolean,
        val topMenuIsVisible: Boolean
)