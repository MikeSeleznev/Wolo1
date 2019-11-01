package com.wolo.a222.feature.rules.presenter

import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.common.presenter.View
import io.reactivex.Flowable

interface RulesPresenter : BasePresenter<RulesView> {
    fun viewState(): Flowable<RulesState>

    fun closeRules()
}

interface RulesView : View


data class RulesState(
        val loaded: Boolean
)