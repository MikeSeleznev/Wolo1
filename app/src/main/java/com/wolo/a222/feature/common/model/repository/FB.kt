package com.wolo.a222.feature.common.model.repository

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.wolo.a222.Const
import io.reactivex.Single
import io.reactivex.SingleEmitter

class FB constructor(private val fbFirestore: FirebaseFirestore) {

    fun getPacks(): Single<List<DocumentSnapshot>> {

        val dbCollection = fbFirestore.collection(Const.FBCollection)

        return Single.create { emitter: SingleEmitter<List<DocumentSnapshot>> ->
            dbCollection.get().addOnSuccessListener {
                emitter.onSuccess(it.documents)
            }
        }
    }
}


