package com.wolo.a222.Presenter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import android.widget.TextView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import com.google.gson.Gson
import com.wolo.a222.Const
import com.wolo.a222.Model.Firebase.Packs
import com.wolo.a222.Game
import com.wolo.a222.Market.Billing
import com.wolo.a222.R
import com.wolo.a222.Staff.SaveLoadDataJson
import com.wolo.a222.View.Activity.IntroActivity
import com.wolo.a222.View.Activity.MainActivity
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

open class FirebasePresenter: ValueEventListener {

    var view: IntroActivity? = null
    var game: Game? = null
    private lateinit var sPref: SharedPreferences
    private lateinit var mFirebaseDatabase: FirebaseDatabase
    private lateinit var mDatabaseReference: DatabaseReference
    internal lateinit var myObserver: Observer<Packs>
    internal lateinit var pack: Packs

    fun bindView(view: IntroActivity){
        this.view = view
    }

    fun unbindView(view: IntroActivity){
        if (this.view == view){
            this.view = null
        }
    }

    fun startGame(){
        if(game == null){
            game = Game()
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
        FirebaseApp.initializeApp(context)
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mFirebaseDatabase!!.reference


        mDatabaseReference!!.child("packs").addValueEventListener(this)

        val myObservable = Observable.create(
                ObservableOnSubscribe<Packs> { emitter ->
                    mDatabaseReference!!.child("packs")
                            .addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                    Log.i("$\$PACK$$", "onDataChange")
                                    pack = dataSnapshot.getValue(Packs::class.java)!!
                                    emitter.onNext(pack)
                                    if (pack != null) {
                                        Billing().queryPurchases(context)
                                        emitter.onComplete()
                                    }
                                }

                                override fun onCancelled(databaseError: DatabaseError) {

                                }
                            })
                }
        )

        myObserver = object : Observer<Packs> {
            override fun onSubscribe(d: Disposable) {
                val a = "a"
            }

            override fun onNext(s: Packs) {
                val a = "a"
            }

            override fun onError(e: Throwable) {
                val a = "a"
            }

            override fun onComplete() {

                SaveLoadDataJson<Packs>().saveData(pack, context, Const.PACKS)
                val loadingText = (context as IntroActivity).findViewById<TextView>(R.id.loadingText)
                loadingText.setText(R.string.loadingText_success)
                val handler = Handler()
                handler.postDelayed({
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    context.finish()
                    //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    //contextfinish();
                }, 1000)
            }
        }
        myObservable.subscribe(myObserver)
    }

    override fun onCancelled(p0: DatabaseError) {
        //TODO("not implemented")
    }
    override fun onDataChange(p0: DataSnapshot) {
        //TODO("not implemented")
    }
}