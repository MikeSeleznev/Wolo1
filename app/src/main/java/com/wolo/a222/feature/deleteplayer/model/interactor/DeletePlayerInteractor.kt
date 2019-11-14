package com.wolo.a222.feature.deleteplayer.model.interactor

import com.wolo.a222.feature.common.entity.Players

interface DeletePlayerInteractor{

    fun getPlayers(): List<Players>

    fun setPlayers(players: List<Players>)

    fun initDateAfterRemovePlayer()
}