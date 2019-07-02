package com.wolo.a222.Model.Firebase

import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.database.*
import io.reactivex.*

class FB() {

    var mFirebaseDatabase = FirebaseDatabase.getInstance().reference.child("packs")

    fun flowableFB():Flowable<DataSnapshot> {

        return Flowable.create({emitter: FlowableEmitter<DataSnapshot> ->

            mFirebaseDatabase.addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(datasnapShot: DataSnapshot) {
                    emitter.onNext(datasnapShot)
                    emitter.onComplete()
                }
            })


        }, BackpressureStrategy.LATEST)
    }


}