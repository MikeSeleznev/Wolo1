package com.wolo.a222.Model.Firebase

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.wolo.a222.Const
import io.reactivex.*

class FB{

    fun initFB():Flowable<DocumentSnapshot>{

        val db = FirebaseFirestore.getInstance()
        val dbCollection = db.collection(Const.FBCollection)

        return Flowable.create({emitter: FlowableEmitter<DocumentSnapshot> ->
            dbCollection.get().addOnSuccessListener {
               var listOfPacks = it.documents
                for (p in listOfPacks) {
                    emitter.onNext(p)
                }
                emitter.onComplete()

            }
        }, BackpressureStrategy.LATEST)

    }
}