package com.wolo.a222.feature.auth.presenter

import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.common.presenter.View
import io.reactivex.Flowable

interface AuthPresenter : BasePresenter<AuthView>{

    fun initState()

    fun viewState(): Flowable<AuthState>

    fun onStartAuth()

    fun onClickStartPlay(gamers: List<String>)

    fun addNewPlayer(name: String, gamersArray: MutableList<String>)
}

interface AuthView : View

data class AuthState(
        val gamersArray: MutableList<String> ,
        val reverseGamersArray: MutableList<String>
)