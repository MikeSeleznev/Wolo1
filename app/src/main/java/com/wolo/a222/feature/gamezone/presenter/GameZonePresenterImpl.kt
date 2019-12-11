package com.wolo.a222.feature.gamezone.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.navigation.Navigator
import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.gamezone.model.interactor.GameZoneInteractor
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class GameZonePresenterImpl
    @Inject constructor(
           private val navigator: Navigator,
           private val gameZoneInteractor: GameZoneInteractor): BasePresenter<GameZoneView>, GameZonePresenter{
    private val compositeDisposable = CompositeDisposable()

    private val gameZoneSubject = BehaviorRelay.createDefault(GameZoneState())

    private var state: GameZoneState
        set(value) = gameZoneSubject.accept(value)
        get() = gameZoneSubject.value!!

    override fun initState() {
        state = GameZoneState()
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

    override fun whoTurn() {
        val str = StringBuilder()
        var player = gameZoneInteractor.firstPlayer()
        if (gameZoneInteractor.isStartGame()) {
            str.append(gameZoneInteractor.getStringWhoStartGame())
        } else {
            player = gameZoneInteractor.previousPlayer()
            str.append(gameZoneInteractor.getStringWhoContinueGame())
        }
        str.append(" ")
        str.append(player.fullName)
        gameZoneInteractor.setPlayer1(player)
        state = state.copy(startGamePlayer = str.toString())
    }

    override fun numberChoosedPlayer(): Int {
       return game.numberChoosedPlayer
    }

    override fun numberOfPlayers(): Int {
        return game.numberOfPlayers()
    }

    override fun startOnePlay() {
        game.startOnePlay()
            /*game.isStartGame = false
            if (game.previousPlayer == null) {
                game.previousPlayer = game.firstPlayer
            }

            val newDir = getRandomAngle(game.lastDir)
            val degree = newDir % 360
            for (i in 0 until game.numberOfPlayers) {
                if (i == 0) {
                    if (degree <= game.players[i].fromDegreeForPlayer || degree > game.players[i].toDegreeForPlayer) {
                        game.choosedPlayer = game.players[i]
                        break
                    }
                } else {
                    if (degree > game.players[i].fromDegreeForPlayer && degree <= game.players[i].toDegreeForPlayer) {
                        game.choosedPlayer = game.players[i]
                        break
                    }
                }
            }
            game.repeatPlayer = if (game.previousPlayer == null) {
                false
            } else {
                game.choosedPlayer!!.fullName == game.previousPlayer!!.fullName
            }
            game.choosedPlayer?.let { game.setPlayer2(it) }*/
    }

    private fun getRandomAngle(lD: Float): Float {
        val random = Random()
        return (random.nextInt(2160) + 1000 + lD.toInt()).toFloat()
    }

    override fun repeatSpin() {
        state = state.copy(startGamePlayer = whoRepeat())
        gameZoneInteractor.setLastDir()
    }

    private fun whoRepeat(): String {
        val str = StringBuilder()
        str.append(gameZoneInteractor.getStringPlayer())
        str.append(" ")
        str.append(gameZoneInteractor.getSecondPlayer().fullName)
        str.append(" ")
        str.append(gameZoneInteractor.getStringWhoRepeatSpinBottle())
        return str.toString()
    }
}