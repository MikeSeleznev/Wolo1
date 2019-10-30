package com.wolo.a222.feature.common.di.module

import android.content.Context
import androidx.room.Room
import com.wolo.a222.feature.common.di.Scope.PerApplication
import com.wolo.a222.feature.common.repository.WoloRepository
import com.wolo.a222.feature.common.storage.WoloDatabase
import com.wolo.a222.feature.common.storage.WoloRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DataModule{

    companion object{
        const val DATABASE_NAME = "WoloDatabase"
    }

    @Provides
    @PerApplication
    fun bindRegistrationRepository(repository: WoloRepositoryImpl): WoloRepository = repository


    @Provides
    @PerApplication
    internal fun provideDatabase(context: Context): WoloDatabase{
        return Room.databaseBuilder(context, WoloDatabase::class.java, DATABASE_NAME)
            .build()
    }
}