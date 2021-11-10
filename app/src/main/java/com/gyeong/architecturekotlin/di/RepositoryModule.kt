package com.gyeong.architecturekotlin.di

import com.gyeong.architecturekotlin.data.repository.local.CntLocalRepositoryImpl
import com.gyeong.architecturekotlin.data.repository.local.WalkRepositoryImpl
import com.gyeong.architecturekotlin.data.repository.remote.TodoRemoteRepositoryImpl
import com.gyeong.architecturekotlin.domain.repository.local.CntLocalRepository
import com.gyeong.architecturekotlin.domain.repository.local.WalkRepository
import com.gyeong.architecturekotlin.domain.repository.remote.TodoRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTodoRepository(
        todoRemoteRepositoryImpl: TodoRemoteRepositoryImpl
    ): TodoRemoteRepository

    @Binds
    @Singleton
    abstract fun bindCntRepository(
        cntLocalRepositoryImpl: CntLocalRepositoryImpl
    ): CntLocalRepository

    @Binds
    @Singleton
    abstract fun bindWalkRepository(
        walkRepositoryImpl: WalkRepositoryImpl
    ): WalkRepository
}