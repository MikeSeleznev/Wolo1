package com.wolo.a222.feature.common.model

import com.wolo.a222.feature.common.entity.Players
import com.wolo.a222.feature.selecttask.presenter.SelectTaskVM
import java.util.*

class Game {
    var tasksVM: List<TasksVM> = emptyList()
    var players: List<Players> = emptyList()
    var choosedPack: SelectTaskVM = SelectTaskVM()
    var isStartGame = true
    private var degree: Float = 0F
    var lastDir = 0f
    var newDir = 0f
    var choosedPlayer: Players? = null
    var previousPlayer: Players? = null
    var repeatPlayer: Boolean = false
    private lateinit var player1: Players
    private lateinit var player2: Players

    var superUser: Boolean = false

    val firstPlayer: Players
        get() = this.players[0]
    val numberChoosedPlayer: Int
        get() = choosedPlayer!!.number


    fun numberOfPlayers(): Int {
        return this.players.size
    }

    fun getRandomQuestion(number: Int): String {
        var task = ""
        for (c in tasksVM) {
            if (c.id == choosedPack.id) {
                task = c.tasks[number]
            }
        }

        val txt: String = task.replace("!1".toRegex(), player1.fullName)
        return txt.replace("!2".toRegex(), player2.fullName)
    }

    private fun calculateAngle() {
        val numberOfPlayers = this.players.size
        val degreeForOnePlayer = (360 / numberOfPlayers).toFloat()
        for (i in 0 until numberOfPlayers) {
            when (i) {
                0 -> {
                    players[i].setFromDegree(0 + degreeForOnePlayer / 2)
                    players[i].setToDegree(360 - degreeForOnePlayer / 2 + 0.1f)
                    players[i].centerDegree = 360f
                }
                1 -> {
                    players[i].setFromDegree(players[i - 1].fromDegreeForPlayer + 0.1f)
                    players[i].setToDegree(players[i - 1].fromDegreeForPlayer + degreeForOnePlayer)
                    players[i].centerDegree = degreeForOnePlayer / 2 * (i + 1)
                }
                else -> {
                    players[i].setFromDegree(players[i - 1].toDegreeForPlayer + 0.1f)
                    players[i].setToDegree(players[i - 1].toDegreeForPlayer + degreeForOnePlayer)
                    players[i].centerDegree = degreeForOnePlayer / 2 * (i + 1)
                }
            }
        }
    }

    private fun getRandomAngle(lD: Float): Float {
        val random = Random()
        newDir = (random.nextInt(2160) + 1000 + lD.toInt()).toFloat()
        return newDir
    }

    fun startOnePlay() {
        this.isStartGame = false

        newDir = getRandomAngle(lastDir)
        degree = newDir % 360
        for (i in players.indices) {
            if (i == 0) {
                if (degree <= players[i].fromDegreeForPlayer || degree > players[i].toDegreeForPlayer) {
                    choosedPlayer = players[i]
                    break
                }
            } else {
                if (degree > players[i].fromDegreeForPlayer && degree <= players[i].toDegreeForPlayer) {
                    choosedPlayer = players[i]
                    break
                }
            }
        }
        repeatPlayer = choosedPlayer == previousPlayer

        choosedPlayer?.let { setPlayer2(it) }

    }

    fun setPrevisionsPlayer() {
        this.previousPlayer = choosedPlayer
    }

    fun setPlayer1(player: Players) {
        this.player1 = player
    }

    private fun setPlayer2(player: Players) {
        this.player2 = player
    }

    fun getPlayer2(): Players {
        return this.player2
    }

    fun initDate(players: List<Players>) {
        this.players = players
        this.choosedPlayer = players[0]
        this.previousPlayer = players[0]
        this.isStartGame = true
        calculateAngle()
    }

    fun initDateAfterRemovePlayer() {
        calculateAngle()
    }
}
