package com.wolo.a222.di

import android.app.Application
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import com.wolo.a222.Storage.Packs.PacksDAO
import com.wolo.a222.Storage.Packs.PacksDatabase
import com.wolo.a222.Storage.Sku.SkuDao
import com.wolo.a222.Storage.Sku.SkuDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(mApplication: Application) {

    private var skuDataBase = Room.databaseBuilder(mApplication, SkuDataBase::class.java, "sku_db").allowMainThreadQueries().build()
    private var packsDatabase = Room.databaseBuilder(mApplication, PacksDatabase::class.java, "packs_db")
            //.addMigrations(PacksDatabase.MIGRATION_1_2)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()


    @Singleton
    @Provides
    fun providesRoomDatabase(): SkuDataBase {
        return skuDataBase}

    @Singleton
    @Provides
    fun providesSkuDao(): SkuDao {return skuDataBase.skuDao()}

    @Singleton
    @Provides
    fun providesPacksDatabase(): PacksDatabase {
        return packsDatabase}

    @Singleton
    @Provides
    fun providesPacksDao(): PacksDAO {return packsDatabase.packsDao()}


}