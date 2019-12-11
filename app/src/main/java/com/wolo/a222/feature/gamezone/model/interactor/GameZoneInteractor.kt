package com.wolo.a222.feature.gamezone.model.interactor

import com.wolo.a222.feature.common.entity.Players

interface GameZoneInteractor{

    fun firstPlayer(): Players

    fun getSecondPlayer(): Players

    fun previousPlayer(): Players

    fun isStartGame(): Boolean

    fun setPlayer1(player: Players)

    fun getStringWhoStartGame(): String

    fun getStringWhoContinueGame(): String

    fun getStringWhoRepeatSpinBottle(): String

    fun getStringPlayer(): String

    fun setLastDir()

}