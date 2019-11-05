package com.wolo.a222.feature.common.model

import com.wolo.a222.feature.common.entity.Players
import com.wolo.a222.feature.selecttask.presenter.SelectTaskVM
import java.util.*

class Game {
    var tasksVM: List<TasksVM> = emptyList()
    var players: List<Players> = emptyList()
    var choosedPack: SelectTaskVM = SelectTaskVM()
    var isStartGame = false
    private var degree: Float = 0F
    var lastDir = 0f
    var newDir = 0f
    var choosedPlayer: Players? = null
    var previsionsPlayer: Players? = null
    var repeatPlayer: Boolean = false
    private lateinit var player1: Players
    private lateinit var player2: Players
    var superUser: Boolean = false

    val firstPlayer: Players
        get() = this.players[0]
    val numberChoosedPlayer: Int
        get() = choosedPlayer!!.number

    init {
        this.isStartGame = true
    }



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
        //cards.removeAt(r1)

        val txt: String = task.replace("!1".toRegex(), player1.fullName)
        return txt.replace("!2".toRegex(), player2.fullName)
    }

    fun whoStartGame(): String {
        val str = StringBuilder()
        str.append("Игру начинает игрок ")
        str.append(firstPlayer.fullName)
        setPlayer1(firstPlayer)
        return str.toString()
    }


    fun whoContinueGame(): String {
        val str = StringBuilder()
        str.append("Теперь очередь игрока ")
        str.append(previsionsPlayer!!.fullName)
        setPlayer1(previsionsPlayer!!)
        return str.toString()
    }

    fun whoRepeat(): String {
        val str = StringBuilder()
        str.append("Игрок ")
        str.append(player2.fullName)
        str.append(" крутит бутылку еще раз ")
        return str.toString()
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
        repeatPlayer = choosedPlayer == previsionsPlayer

        choosedPlayer?.let { setPlayer2(it) }

    }

    fun setLastDir() {
        lastDir = newDir
    }

    fun setPrevisionsPlayer() {
        this.previsionsPlayer = choosedPlayer
    }

    private fun setPlayer1(player: Players) {
        this.player1 = player
    }

    fun setPlayer2(player: Players) {
        this.player2 = player
    }


    fun initDate(players: List<Players>) {
        this.players = players
        this.choosedPlayer = players[0]
        this.previsionsPlayer = players[0]
        this.isStartGame = true
        calculateAngle()
    }

    fun initDateAfterRemovePlayer() {
        calculateAngle()
    }
}
