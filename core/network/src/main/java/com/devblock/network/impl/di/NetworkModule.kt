package com.devblock.network.impl.di

import com.devblock.network.api.*
import com.devblock.network.impl.ApiService
import com.devblock.network.impl.ExceptionCallAdapterFactory
import com.devblock.network.impl.api.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkModule {


    @Binds
    fun provideUserService(userService: DefaultContactApi): ContactApi

    companion object {

        @Singleton
        @Provides
        fun provideApiService(client: OkHttpClient): ApiService =
            Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .client(client)
                .addCallAdapterFactory(ExceptionCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)

        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build()
    }
}