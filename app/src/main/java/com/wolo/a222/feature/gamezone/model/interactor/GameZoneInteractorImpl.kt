package com.wolo.a222.feature.gamezone.model.interactor

import android.content.Context
import com.wolo.a222.R
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.di.scope.PerFeature
import com.wolo.a222.feature.common.entity.Players
import javax.inject.Inject


@PerFeature
class GameZoneInteractorImpl @Inject constructor(private val context: Context) : GameZoneInteractor {

    override fun firstPlayer(): Players {
        return game.firstPlayer
    }

    override fun getSecondPlayer(): Players {
        return game.getPlayer2()
    }

    override fun previousPlayer(): Players {
        return game.previousPlayer!!
    }

    override fun isStartGame(): Boolean {
        return game.isStartGame
    }

    override fun setPlayer1(player: Players) {
        game.setPlayer1(player)
    }

    override fun getStringWhoStartGame(): String {
        return context.resources.getString(R.string.who_start_game_text)
    }

    override fun getStringWhoContinueGame(): String {
        return context.resources.getString(R.string.who_continue_game_text)
    }

    override fun getStringWhoRepeatSpinBottle(): String {
        return context.resources.getString(R.string.repeat_spin_the_bottle_text)
    }

    override fun getStringPlayer(): String {
        return context.resources.getString(R.string.player_text)
    }

    override fun setLastDir() {
        game.lastDir = game.newDir
    }
}