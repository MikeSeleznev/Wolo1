package com.wolo.a222.Presenter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.widget.TextView
import com.google.gson.Gson
import com.wolo.a222.Const
import com.wolo.a222.Model.Firebase.Packs
import com.wolo.a222.Game
import com.wolo.a222.Model.Firebase.InitFB
import com.wolo.a222.R
import com.wolo.a222.Staff.SaveLoadDataJson
import com.wolo.a222.View.Activity.IntroActivity
import com.wolo.a222.View.Activity.MainActivity
import io.reactivex.schedulers.*

open class FirebasePresenter {

    var view: IntroActivity? = null
    var game: Game? = null
    private lateinit var sPref: SharedPreferences
    private lateinit var context: Context

    fun bindView(view: IntroActivity, context: Context){
        this.view = view
        this.context = context
    }

    fun unbindView(view: IntroActivity){
        if (this.view == view){
            this.view = null
        }
    }

    fun startGame(){
        if(game == null){
            game = Game()
            saveData()
        }
    }

    fun saveData(){
        val gson = Gson()
        val json = gson.toJson(game)
        sPref = PreferenceManager.getDefaultSharedPreferences(view)
        val ed = sPref.edit()
        ed.putString(Const.GAME, json)
        ed.commit()
    }

    fun loadData(){

    }

    fun init(context: Context){

        var connection = hasConnection()

        if (connection == true){
            InitFB().FlowableInitFB(context)
                    .observeOn(Schedulers.io())
                    .doOnNext {
                        SaveLoadDataJson<Packs>().saveData(it, context, Const.PACKS)
                    }
                    .doOnError { }
                    .doOnComplete {
                        val loadingText = (context as IntroActivity).findViewById<TextView>(R.id.loadingText)
                        loadingText.setText(R.string.loadingText_success)
                        val handler = Handler(Looper.getMainLooper())
                        handler.postDelayed({
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                            context.finish()
                        }, 1000)

                    }
                    .subscribe()
        } else {
            var packs = Packs()
            packs.setCards(context)
            SaveLoadDataJson<Packs>().saveData(packs, context, Const.PACKS)
            val loadingText = (context as IntroActivity).findViewById<TextView>(R.id.loadingText)
            loadingText.setText(R.string.loadingText_success)
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
                context.finish()
            }, 2000)
        }
    }

    fun hasConnection(): Boolean {
        var cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = cm.activeNetworkInfo

        return networkInfo!=null && networkInfo.isConnected
    }
}