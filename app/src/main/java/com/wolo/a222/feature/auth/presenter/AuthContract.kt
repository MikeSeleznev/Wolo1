package com.wolo.a222.feature.auth.presenter

import com.wolo.a222.feature.common.presenter.Presenter
import com.wolo.a222.feature.common.presenter.View
import io.reactivex.Flowable

interface AuthPresenter : Presenter<AuthView>{

    fun initState()

    fun viewState(): Flowable<AuthState>

    fun onStartAuth()

    fun onClickStartPlay(gamers: List<String>)

}

interface AuthView : View

data class AuthState(
        val loaded: Boolean
)