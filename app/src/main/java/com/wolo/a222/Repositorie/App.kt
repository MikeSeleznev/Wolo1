package com.wolo.a222.Repositorie

import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(Sku::class), version = 1)
abstract class App: RoomDatabase(){
    abstract fun SkuDao(): SkuDao
   companion object{
       private var INSTANCE: App? = null
       fun getDataBase(context: Context): App?{
           if (INSTANCE == null){
               INSTANCE = Room.databaseBuilder(context.applicationContext, App::class.java, "database.db")
                       .allowMainThreadQueries()
                           .build()

           }
           return INSTANCE
       }


   }
}