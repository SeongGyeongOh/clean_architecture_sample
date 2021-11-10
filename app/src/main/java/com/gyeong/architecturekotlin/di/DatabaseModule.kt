package com.gyeong.architecturekotlin.di

import android.content.Context
import com.gyeong.architecturekotlin.data.db.CntDao
import com.gyeong.architecturekotlin.data.db.CommonDatabase
import com.gyeong.architecturekotlin.data.db.WalkDao
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
    fun provideCntDatabase(context: Context): CommonDatabase {
        return CommonDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideCntDao(todoDatabase: CommonDatabase): CntDao {
        return todoDatabase.cntDao()
    }

    @Provides
    @Singleton
    fun provideWalkDao(walkDatabase: CommonDatabase): WalkDao {
        return walkDatabase.walkDao()
    }
}