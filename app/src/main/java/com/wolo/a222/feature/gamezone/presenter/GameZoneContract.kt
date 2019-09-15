package com.wolo.a222.feature.gamezone.presenter

import com.wolo.a222.feature.common.presenter.Presenter
import com.wolo.a222.feature.common.presenter.View
import io.reactivex.Flowable

interface GameZonePresenter: Presenter<GameZoneView>{

    fun initState()

    fun viewState(): Flowable<GameZoneState>

    fun showDecks()

    fun whoStartGame(): String

    fun numberChoosedPlayer(): Int

    fun numberOfPlayers(): Int

    fun startOnePlay()
}

interface GameZoneView: View

data class GameZoneState(
        var loaded: Boolean
)
