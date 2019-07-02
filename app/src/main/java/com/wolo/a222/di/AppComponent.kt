package com.wolo.a222.di

import com.wolo.a222.Storage.Packs.PacksDAO
import com.wolo.a222.Storage.Packs.PacksDatabase
import com.wolo.a222.Storage.Sku.SkuDao
import com.wolo.a222.Storage.Sku.SkuDataBase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class])
interface AppComponent {

    //fun inject(introActivity: IntroActivity)

    fun skuDao(): SkuDao

    fun skuDataBase(): SkuDataBase

    fun packsDao(): PacksDAO

    fun packsDatabase(): PacksDatabase

   // fun application(): Application
}