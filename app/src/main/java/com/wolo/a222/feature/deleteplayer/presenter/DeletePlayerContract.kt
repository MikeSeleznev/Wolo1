package com.wolo.a222.feature.deleteplayer.presenter

import com.wolo.a222.feature.common.entity.Players
import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.common.presenter.View
import com.wolo.a222.feature.rules.presenter.RulesState
import io.reactivex.Flowable

interface DeletePlayerPresenter : BasePresenter<DeletePlayerView> {
    fun viewState(): Flowable<DeletePlayerState>

    fun closeDeletePlayer()

    fun deletePlayer(item: Int)
}

interface DeletePlayerView : View


data class DeletePlayerState(
    val gamersArray: List<String> = emptyList(),
    val gamersList: List<Players> = emptyList()
)