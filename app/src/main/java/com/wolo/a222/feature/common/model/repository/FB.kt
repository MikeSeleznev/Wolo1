package com.wolo.a222.feature.common.model.repository

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.wolo.a222.feature.common.model.Cards
import com.wolo.a222.Const
import com.wolo.a222.WoloApp.Companion.game
import io.reactivex.*

class FB constructor(private val fbFirestore: FirebaseFirestore) {

    fun getPacks(): Single<List<DocumentSnapshot>> {

        val dbCollection = fbFirestore.collection(Const.FBCollection)

        return Single.create { emitter: SingleEmitter<List<DocumentSnapshot>> ->
            dbCollection.get().addOnSuccessListener {
                emitter.onSuccess(it.documents)
            }
        }
    }

    fun addCardsToGame(listSnapshot : List<DocumentSnapshot>){
        var listCards = mutableListOf<Cards>()
        for (item in listSnapshot)
        {
            var newCard = Cards(item.data?.get("name") as String, item.data?.get("cards") as ArrayList<String>)
            listCards.add(newCard)
        }
        game.cards = listCards.toTypedArray()
    }
}


