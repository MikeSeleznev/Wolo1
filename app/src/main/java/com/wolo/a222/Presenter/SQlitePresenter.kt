package com.wolo.a222.Presenter

import android.app.Application
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.wolo.a222.Repositorie.AppDatabase
import com.wolo.a222.View.Activity.IntroActivity

class SQlitePresenter (): Application() {

    var view: IntroActivity? = null


    fun bindView(view: IntroActivity){
        this.view = view
    }

    fun createBase(){
        var db: RoomDatabase.Builder<AppDatabase> = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database")

    }
}