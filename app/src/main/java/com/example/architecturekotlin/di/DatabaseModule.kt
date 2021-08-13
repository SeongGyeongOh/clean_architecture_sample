package com.example.architecturekotlin.di

import android.content.Context
import com.example.architecturekotlin.data.db.CntDao
import com.example.architecturekotlin.data.db.CntDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCntDatabase(context: Context): CntDatabase {
        return CntDatabase.getDatabase(context)
    }

    @Provides
    fun provideCntDao(todoDatabase: CntDatabase): CntDao {
        return todoDatabase.cntDao()
    }
}