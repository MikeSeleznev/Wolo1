package com.wolo.a222.feature.deleteplayer.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.navigation.Navigator
import com.wolo.a222.feature.common.presenter.BasePresenter
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DeletePlayerPresenterImpl @Inject constructor(
    private val navigator: Navigator
) : BasePresenter<DeletePlayerView>, DeletePlayerPresenter {

    private val compositeDisposable = CompositeDisposable()
    private val deletePlayersSubject = BehaviorRelay.createDefault(DeletePlayerState())

    private var state: DeletePlayerState
        set(value) = deletePlayersSubject.accept(value)
        get() = deletePlayersSubject.value!!

    override fun onFinish() {
        compositeDisposable.clear()
    }

    override fun viewState(): Flowable<DeletePlayerState> {
        return deletePlayersSubject.toFlowable(BackpressureStrategy.LATEST)
                .onBackpressureBuffer(3)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    if (compositeDisposable.size() == 0) {
                        initialLoadSettings()
                    }
                }
    }

    private fun initialLoadSettings() {
        val players = game.players.map {
            it.fullName
        }
            state = state.copy(gamersArray = players, gamersList = game.players)
    }

    override fun closeDeletePlayer() {
        if (state.gamersList.size > 2) {
            game.players = state.gamersList
            game.initDateAfterRemovePlayer()
        }
        navigator.closeDeletePlayer()
    }

    override fun deletePlayer(item: Int) {
        val newArray = state.gamersArray.toMutableList()
        newArray.removeAt(item)
        val newPlayers = state.gamersList.toMutableList()
        newPlayers.removeAt(item)
        state = state.copy(gamersArray = newArray, gamersList = newPlayers)
    }
}