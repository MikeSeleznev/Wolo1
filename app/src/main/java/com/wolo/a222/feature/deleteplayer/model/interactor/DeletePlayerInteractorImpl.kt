package com.wolo.a222.feature.deleteplayer.model.interactor

import com.wolo.a222.WoloApp
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.di.Scope.PerFeature
import com.wolo.a222.feature.common.entity.Players
import javax.inject.Inject

@PerFeature
class DeletePlayerInteractorImpl @Inject constructor(
) : DeletePlayerInteractor {

    override fun getPlayers(): List<Players> {
        return game.players
    }

    override fun setPlayers(players: List<Players>) {
        game.players = players
    }

    override fun initDateAfterRemovePlayer() {
        game.initDateAfterRemovePlayer()
    }
}