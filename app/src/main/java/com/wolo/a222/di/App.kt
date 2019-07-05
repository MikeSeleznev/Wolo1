package com.wolo.a222.di

import android.app.Application
import android.arch.persistence.room.migration.Migration
import com.wolo.a222.Storage.UtilsDB

class App: Application() {

    companion object{

        lateinit var utilsDB: UtilsDB

        private lateinit var component: AppComponent

        internal fun getComponent(): AppComponent {
            return component
        }

    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                //.appModule(AppModule(this))
                .roomModule(RoomModule(this)).build()

        utilsDB = UtilsDB()
    }
}

