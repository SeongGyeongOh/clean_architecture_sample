package com.example.architecturekotlin.di

import android.content.Context
import com.example.architecturekotlin.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApp(@ApplicationContext app: Context): App {
        return app as App
    }

    @Provides
    @Singleton
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

}