package com.wolo.a222.feature.auth.presenter

import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.common.presenter.View
import io.reactivex.Flowable

interface AuthPresenter : BasePresenter<AuthView>{

    fun initState()

    fun viewState(): Flowable<AuthState>

    fun onStartAuth()

    fun onClickStartPlay(gamers: List<String>)

    fun addNewPlayer(name: String)

    fun deletePlayer(id: Int)

    fun activeSuperUser()

    fun refreshState()
}

interface AuthView : View

data class AuthState(
        val gamersArray: List<String> = emptyList(),
        val reverseGamersArray: List<String> = emptyList()
)