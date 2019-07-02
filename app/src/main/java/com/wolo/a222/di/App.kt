package com.wolo.a222.di

import android.app.Application

class App: Application() {

    companion object{
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
    }
}

