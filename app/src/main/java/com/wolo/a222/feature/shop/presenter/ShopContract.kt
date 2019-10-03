package com.wolo.a222.feature.shop.presenter

import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.common.presenter.View
import io.reactivex.Flowable

interface ShopPresenter: BasePresenter<ShopView> {
    fun viewState(): Flowable<ShopState>

    fun initState()
}

interface ShopView: View


data class ShopState(
        val loaded: Boolean
)