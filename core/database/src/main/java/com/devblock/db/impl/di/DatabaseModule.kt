package com.devblock.db.impl.di

import android.content.Context
import androidx.room.Room
import com.devblock.db.api.ContactRepository
import com.devblock.db.api.ProfileRepository
import com.devblock.db.impl.AppDatabase
import com.devblock.db.impl.repositories.DefaultContactRepository
import com.devblock.db.impl.repositories.DefaultProfileRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DatabaseModule {


    @Binds
    fun provideFavoriteRepository(repository: DefaultContactRepository): ContactRepository

    @Binds
    fun provideProfileRepository(repository: DefaultProfileRepository): ProfileRepository



    companion object {
        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
            Room
                .databaseBuilder(context, AppDatabase::class.java, "YammyDatabase")
                .fallbackToDestructiveMigration()
                .build()

        @Provides
        fun provideContactDao(database: AppDatabase) = database.contactDao()

        @Provides
        fun provideProfileDao(database: AppDatabase) = database.profileDao()


    }
}