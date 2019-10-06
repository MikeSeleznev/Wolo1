package com.wolo.a222.feature.gamezone.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.navigation.Navigator
import com.wolo.a222.feature.common.presenter.BasePresenter
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GameZonePresenterImpl
    @Inject constructor(
           val navigator: Navigator ): BasePresenter<GameZoneView>, GameZonePresenter{
    private val compositeDisposable = CompositeDisposable()

    private val gameZoneSubject = BehaviorRelay.createDefault(GameZoneState(true))

    private var state: GameZoneState
        set(value) = gameZoneSubject.accept(value)
        get() = gameZoneSubject.value!!

    override fun initState() {
        state = GameZoneState(true)
    }

    override fun onFinish(){
        compositeDisposable.clear()
    }

    override fun viewState(): Flowable<GameZoneState> {
        return gameZoneSubject.toFlowable(BackpressureStrategy.LATEST)
                .onBackpressureBuffer(3)
                .subscribeOn(Schedulers.io())
    }

    override fun showDecks() {
        navigator.showDecks()
    }

    override fun whoTurn(): String {

        return if (game.isStartGame!!){
            game.whoStartGame()
        }
        else {
            game.whoContinueGame()
        }

    }

    override fun numberChoosedPlayer(): Int {
       return game.numberChoosedPlayer
    }

    override fun numberOfPlayers(): Int {
        return game.numberOfPlayers()
    }

    override fun startOnePlay() {
        game.startOnePlay()
    }
}