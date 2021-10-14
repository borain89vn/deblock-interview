package com.devblock.navigation.impl

import com.devblock.navigation.Navigator
import com.devblock.navigation.api.model.Destination
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DefaultNavigator @Inject constructor() : Navigator {

    override val destination: MutableSharedFlow<Destination> = MutableSharedFlow(1)
    override val currentDestination: Destination?
        get() = destination.replayCache.firstOrNull()

    override fun back() {
        destination.tryEmit(Destination.Back)
    }

    override fun goTo(destination: Destination) {
        this.destination.tryEmit(destination)
    }
}