package com.wolo.a222.Presenter

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.wolo.a222.Const
import com.wolo.a222.Model.Firebase.Packs
import com.wolo.a222.Players
import com.wolo.a222.Staff.SaveLoadDataJson
import com.wolo.a222.feature.common.model.Cards
import com.wolo.a222.feature.common.model.Game
import com.wolo.a222.feature.common.view.MainActivity

open class MainActivityPresenter(){

    private lateinit var context: Context
    var view: MainActivity? = null

    fun bindView(view: MainActivity, context: Context){
        this.view = view
        this.context = context
    }

    fun unbindView(view: MainActivity){
        if (this.view == view){
            this.view = null
        }
    }

    fun setData(context: Context, gamersArray: ArrayList<String>){

        val gson = Gson()
        val json = PreferenceManager.getDefaultSharedPreferences(context).getString(Const.GAME, "")
        var game = gson.fromJson(json, Game::class.java)

        setCards(context, game)
        addPlayers(gamersArray, game)
        game.calculateAngle()

        SaveLoadDataJson<Game>().saveData(game, context, Const.GAME)
    }

    fun setCards(context: Context, game: Game){

        val gsonS = Gson()
        val jsonS = PreferenceManager.getDefaultSharedPreferences(context).getString(Const.PACKS, "")
        val packs = gsonS.fromJson(jsonS, Packs::class.java)

        var cards = arrayOfNulls<Cards>(5)

        game.addCards(cards)


    }

    fun addPlayers(gamersArray: ArrayList<String>, game: Game) {

       var players = arrayOfNulls<Players>(gamersArray.size)
        var  j = 0
        for (i in gamersArray) {
            players[j] = Players(i, j + 1)
            j+=1
        }
        game.selectedPlayer = players[0]
        game.addPlayers(players)

    }
}