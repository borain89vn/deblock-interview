package com.devblock.navigation.impl.di

import com.devblock.navigation.Navigator
import com.devblock.navigation.impl.DefaultNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {

    @Singleton
    @Binds
    fun provideNavigator(navigator: DefaultNavigator): Navigator
}