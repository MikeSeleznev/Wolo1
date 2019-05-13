package com.wolo.a222.Firebase;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wolo.a222.Const;
import com.wolo.a222.IntroActivity;
import com.wolo.a222.MainActivity;
import com.wolo.a222.R;
import com.wolo.a222.Staff.SaveLoadDataJson;

import android.content.Intent;
import android.os.Handler;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class InitFB {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    Observer<Packs> myObserver;
    Packs pack;

    public InitFB(Context context){

        initFirebase(context);
    }

    private void initFirebase(final Context context){
        FirebaseApp.initializeApp(context);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        Observable<Packs> myObservable = Observable.create(
                new ObservableOnSubscribe<Packs>() {
                    @Override
                    public void subscribe(final ObservableEmitter<Packs> emitter) throws Exception {
                        mDatabaseReference.child("packs")
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Log.i("$$PACK$$", "onDataChange");
                                        pack = dataSnapshot.getValue(Packs.class);
                                        emitter.onNext(pack);
                                        if (!(pack == null)){
                                            emitter.onComplete();}
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                    }
                }
        );

        myObserver = new Observer<Packs>() {
            @Override
            public void onSubscribe(Disposable d) {
                String a = "a";
            }

            @Override
            public void onNext(Packs s) {
                String a = "a";
            }

            @Override
            public void onError(Throwable e) {
                String a = "a";
            }

            @Override
            public void onComplete() {

                new SaveLoadDataJson<Packs>().saveData(pack, context, Const.PACKS);
                TextView loadingText = ((IntroActivity)context).findViewById(R.id.loadingText);
                loadingText.setText(R.string.loadingText_success);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                ((IntroActivity) context).finish();
                //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                //contextfinish();
            }
        }, 500);
            }
        };
            myObservable.subscribe(myObserver);
    }
}
