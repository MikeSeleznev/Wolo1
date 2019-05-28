package com.wolo.a222.Model.Firebase

import android.content.Context
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import com.wolo.a222.Market.Billing
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import java.util.concurrent.Callable

class InitFB  {


    private lateinit var mFirebaseDatabase: FirebaseDatabase
    private lateinit var mDatabaseReference: DatabaseReference
    internal lateinit var pack: Packs

    /*fun FlowableInitFB(context: Context): Flowable<Pack> {

        FirebaseApp.initializeApp(context)
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mFirebaseDatabase!!.reference


        mDatabaseReference!!.child("packs").addValueEventListener(this)

        val flowableInitFB: Flowable<Packs> = Flowable.fromCallable(Callable () {
            mDatabaseReference!!.child("packs")
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            Log.i("$\$PACK$$", "onDataChange")
                            pack = dataSnapshot.getValue(Packs::class.java)!!
                            //emitter.onNext(pack)
                            if (pack != null) {
                                Billing().queryPurchases(context)
                                // emitter.onComplete()
                            }
                        }

                        override fun onCancelled(databaseError: DatabaseError) {

                        }
                    })
                pack
        })



        val myObservable = Observable.fromCallable {
            (
                    ObservableOnSubscribe<Packs> { emitter ->
                        mDatabaseReference!!.child("packs")
                                .addValueEventListener(object : ValueEventListener {
                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                        Log.i("$\$PACK$$", "onDataChange")
                                        pack = dataSnapshot.getValue(Packs::class.java)!!
                                        //emitter.onNext(pack)
                                        if (pack != null) {
                                            Billing().queryPurchases(context)
                                           // emitter.onComplete()
                                        }
                                    }

                                    override fun onCancelled(databaseError: DatabaseError) {

                                    }
                                })
                    }
                    )
        }

        return flowableInitFB
    }*/

    /*override fun onCancelled(p0: DatabaseError) {
        //TODO("not implemented")
    }

    override fun onDataChange(p0: DataSnapshot) {
        //TODO("not implemented")
    }*/
}