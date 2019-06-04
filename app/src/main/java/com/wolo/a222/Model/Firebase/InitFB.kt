package com.wolo.a222.Model.Firebase

import android.content.Context
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import com.wolo.a222.Market.Billing
import io.reactivex.*
import java.util.concurrent.Callable

class InitFB: ValueEventListener   {

    private lateinit var mFirebaseDatabase: FirebaseDatabase
    private lateinit var mDatabaseReference: DatabaseReference
    internal lateinit var pack: Packs

    fun FlowableInitFB(context: Context): Flowable<Packs> = Flowable.create({emitter: FlowableEmitter<Packs> ->


        FirebaseApp.initializeApp(context)
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mFirebaseDatabase!!.reference


        //mDatabaseReference!!.child("packs").addValueEventListener(this)

        mDatabaseReference!!.child("packs")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        Log.i("$\$PACK$$", "onDataChange")
                        pack = dataSnapshot.getValue(Packs::class.java)!!
                        Billing().queryPurchases(context)
                        emitter.onNext(pack)
                        emitter.onComplete()
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        pack.setCards(context) //TODO загрузка карт из приложения если не удалось загрузить из интернета
                        emitter.onNext(pack)
                        emitter.onComplete()
                    }

                }

                )
    }, BackpressureStrategy.LATEST)

    override fun onCancelled(p0: DatabaseError) {
        var a = "f"//TODO("not implemented")
    }
    override fun onDataChange(p0: DataSnapshot) {
        var a = "f"//TODO("not implemented")
    }

}