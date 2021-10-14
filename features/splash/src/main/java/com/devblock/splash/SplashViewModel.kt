package com.devblock.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devblock.navigation.Navigator
import com.devblock.navigation.api.model.Destination
import com.devblock.preferences.api.DatastoreRepository
import com.devblock.splash.model.SplashState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
internal class SplashViewModel @Inject constructor(
    private val datastore: DatastoreRepository,
    private val navigator: Navigator,
) : ViewModel() {

    private val _state = MutableStateFlow<SplashState>(SplashState.Loading)
    val state: StateFlow<SplashState>
        get() = _state
    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(MIN_SPLASH_DURATION)
            _state.value = SplashState.Idle
            if (datastore.isAuthorized()) {
                navigator.goTo(Destination.Welcome)
            } else  navigator.goTo(Destination.Login())


        }
    }

    companion object {
        private const val MIN_SPLASH_DURATION = 3000L
    }
}