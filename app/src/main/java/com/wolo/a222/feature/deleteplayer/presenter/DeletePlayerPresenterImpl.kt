package com.wolo.a222.feature.deleteplayer.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
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
        val playersList = deletePlayerInteractor.getPlayers()
        val playersArray = playersList.map { it.fullName }

        state = state.copy(gamersArray = playersArray, gamersList = playersList)
    }

    override fun closeDeletePlayer() {
        if (state.gamersList.size > 2) {
            deletePlayerInteractor.initDateAfterRemovePlayer()
        }
        navigator.closeDeletePlayer()
    }

    override fun deletePlayer(item: Int) {
        val playersList = deletePlayerInteractor.getPlayers().toMutableList()
        playersList.removeAt(item)
        deletePlayerInteractor.setPlayers(playersList.toList())
        val gamersArray = playersList.map { it.fullName }
        state = state.copy(gamersArray = gamersArray, gamersList = playersList)
    }
}