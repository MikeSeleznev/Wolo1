package com.wolo.a222.feature.shop.presenter

import android.app.Activity
import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.common.presenter.View
import com.wolo.a222.feature.common.entity.SkuDeck
import io.reactivex.Flowable

interface ShopPresenter: BasePresenter<ShopView> {
    fun viewState(): Flowable<ShopState>

    fun initState()

    fun closeShop()

    fun buyDeck(i: SkuDeck, act: Activity)
}

interface ShopView: View


data class ShopState(
        val loaded: Boolean,
        val skuDeck: List<SkuDeck> = emptyList()
)