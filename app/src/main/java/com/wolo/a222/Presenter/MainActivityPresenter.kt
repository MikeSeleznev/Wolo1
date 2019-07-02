package com.wolo.a222.Presenter

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.wolo.a222.*
import com.wolo.a222.Model.Firebase.Packs
import com.wolo.a222.Staff.SaveLoadDataJson
import com.wolo.a222.View.Activity.MainActivity
import java.util.zip.CheckedInputStream

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
        if (packs != null) {
            cards[0] = Cards(Const.USUAL, packs.usuallStringArray)
            cards[1] = Cards(Const.EXTREME, packs.extremeStringArray)
            cards[2] = Cards(Const.SPORT, packs.sportStringArray)
            cards[3] = Cards(Const.EROTIC, packs.eroticStringArray)
            cards[4] = Cards(Const.OHFUCK, packs.ohfuckStringArray)
        } else {
            cards[0] = Cards(Const.USUAL, context.resources.getStringArray(R.array.usuall))
            cards[1] = Cards(Const.EXTREME, context.resources.getStringArray(R.array.extreme))
        }

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