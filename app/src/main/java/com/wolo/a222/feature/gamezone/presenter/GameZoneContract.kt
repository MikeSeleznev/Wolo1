package com.wolo.a222.feature.gamezone.presenter

import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.common.presenter.View
import io.reactivex.Flowable

interface GameZonePresenter: BasePresenter<GameZoneView>{

    fun initState()

    fun viewState(): Flowable<GameZoneState>

    fun showDecks()

    fun whoTurn()

    fun numberChoosedPlayer(): Int

    fun numberOfPlayers(): Int

    fun startOnePlay()
}

interface GameZoneView: View

data class GameZoneState(
        var startGamePlayer: String = ""
)
