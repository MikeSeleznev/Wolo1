package com.wolo.a222.feature.gamezone.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.navigation.Navigator
import com.wolo.a222.feature.common.presenter.BasePresenter
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
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
            /*game.isStartGame = false
            if (game.previsionsPlayer == null) {
                game.previsionsPlayer = game.firstPlayer
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
            game.repeatPlayer = if (game.previsionsPlayer == null) {
                false
            } else {
                game.choosedPlayer!!.fullName == game.previsionsPlayer!!.fullName
            }
            game.choosedPlayer?.let { game.setPlayer2(it) }*/
    }

    private fun getRandomAngle(lD: Float): Float {
        val random = Random()
        return (random.nextInt(2160) + 1000 + lD.toInt()).toFloat()
    }
}