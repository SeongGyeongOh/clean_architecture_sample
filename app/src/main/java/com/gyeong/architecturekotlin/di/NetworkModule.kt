package com.gyeong.architecturekotlin.di

import android.content.Context
import com.gyeong.architecturekotlin.R
import com.gyeong.architecturekotlin.data.api.TodoApi
import com.gyeong.architecturekotlin.network.HeaderInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideTodoApi(
        @Named("OpenApi") retrofit: Retrofit
    ): TodoApi {
        return retrofit.create(TodoApi::class.java)
    }

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    @Named("OpenApi")
    fun provideRetrofit(
        @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient,
        context: Context
    ): Retrofit {
        val contentType = "application/json; charset=utf-8".toMediaType()
        return Retrofit
            .Builder()
            .baseUrl(context.getString(R.string.apiUrl))
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    @AuthInterceptorOkHttpClient
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }

}