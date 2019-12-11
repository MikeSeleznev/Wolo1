package com.wolo.a222.feature.deleteplayer.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.navigation.Navigator
import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.deleteplayer.model.interactor.DeletePlayerInteractor
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DeletePlayerPresenterImpl @Inject constructor(
    private val navigator: Navigator,
    private val deletePlayerInteractor: DeletePlayerInteractor
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
        val players = deletePlayerInteractor.getPlayers()
        val playersNames = players.map { it.fullName }
        state = state.copy(gamersArray = playersNames, gamersList = players)
    }

    override fun closeDeletePlayer() {
        deletePlayerInteractor.initDateAfterRemovePlayer()
        navigator.closeDeletePlayer()
    }

    override fun deletePlayer(item: Int) {
        val players = deletePlayerInteractor.getPlayers().toMutableList()
        if (players.size > 2) {
            players.removeAt(item)
            deletePlayerInteractor.setNewPlayers(players)
            initialLoadSettings()
        }
    }
}