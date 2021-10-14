package com.devblock.interview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.devblock.contact.ContactFragmentDirections
import com.devblock.navigation.Navigator
import com.devblock.navigation.api.model.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class NavigationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    navigator: Navigator
) : ViewModel() {

    var navController: NavController? = null

    private val currentDestination
        get() = navController?.currentBackStackEntry?.destination?.id

    private var targetDestination: Destination?
        get() = savedStateHandle.get(TARGET_DESTINATION)
        set(value) = savedStateHandle.set(TARGET_DESTINATION, value)

    init {
        navigator.destination
            .onEach { destination ->
                val navController = navController ?: return@onEach

                if (destination is Destination.Login && destination.targetDestination != null) {
                    targetDestination = destination.targetDestination
                    navigateTo(
                        navController = navController,
                        destination = destination,
                        navOptions = NavOptions.Builder()
                            .setPopUpTo(currentDestination!!, true)
                            .build()
                    )
                } else {
                    if (
                        destination !is Destination.Login &&

                        destination !is Destination.LoginTarget
                    ) {
                        targetDestination = null
                    }
                    navigateTo(
                        navController = navController,
                        destination = destination
                    )
                }
            }
            .launchIn(viewModelScope)
    }


    private fun navigateTo(
        navController: NavController,
        destination: Destination,
        navOptions: NavOptions? = null
    ) {
        when (destination) {
            Destination.Welcome -> {
                if (currentDestination == R.id.splashFragment) {
                    navController.popBackStack()
                }
                navController.navigate(MainGraphDirections.toWelcome(), navOptions)
            }

            is Destination.Login -> {
                if (currentDestination == R.id.splashFragment) {
                    navController.popBackStack()
                }
                navController.navigate(MainGraphDirections.toLogin(), navOptions)
            }

            Destination.Back -> {
                navController.navigateUp()
            }

            is Destination.Contact -> {
                navController.navigate(
                    ContactFragmentDirections.toContact(
                        id = destination.id,
                        name = destination.name?:"",
                        email = destination.email?:"",
                        avatar = destination.avatar?:""
                    ),
                    navOptions
                )
            }
            is Destination.LoginTarget -> {
                val targetDestination = targetDestination
                if (targetDestination != null) {
                    navigateTo(
                        navController = navController,
                        destination = targetDestination,
                        navOptions = NavOptions.Builder()
                            .setPopUpTo(currentDestination!!, true)
                            .build()
                    )
                } else {
                    navController.navigateUp()
                }
            }
        }
    }

    companion object {
        private const val TARGET_DESTINATION = "targetDestination"
    }
}
