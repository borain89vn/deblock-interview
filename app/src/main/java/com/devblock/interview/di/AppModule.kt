package com.devblock.interview.di


import com.devblock.DefaultAppLauncher
import com.devblock.utils.AppLauncher
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun provideAppLauncher(appLauncher: DefaultAppLauncher): AppLauncher

    companion object {
        @Provides
        fun provideApplicationScope(): CoroutineScope =
            CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }
}