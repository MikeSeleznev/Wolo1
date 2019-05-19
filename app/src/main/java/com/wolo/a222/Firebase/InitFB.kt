package com.wolo.a222.Firebase

import android.content.Context
import android.content.Intent
import android.util.Log

import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.wolo.a222.Const
import com.wolo.a222.IntroActivity
import com.wolo.a222.MainActivity
import com.wolo.a222.R
import com.wolo.a222.Staff.SaveLoadDataJson
import android.os.Handler
import android.widget.TextView

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class InitFB(context: Context) : ValueEventListener {

    private lateinit var mFirebaseDatabase: FirebaseDatabase
    private lateinit var mDatabaseReference: DatabaseReference
    internal lateinit var myObserver: Observer<Packs>
    internal lateinit var pack: Packs

    init {

        initFirebase(context)
    }

    public fun initFirebase(context: Context) {
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
                }, 500)
            }
        }
        myObservable.subscribe(myObserver)
    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {

    }

    override fun onCancelled(databaseError: DatabaseError) {

    }
}
