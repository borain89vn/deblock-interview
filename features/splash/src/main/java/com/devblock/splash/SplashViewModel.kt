package com.devblock.splash


import androidx.lifecycle.viewModelScope
import com.devblock.base.BaseViewModel
import com.devblock.navigation.Navigator
import com.devblock.navigation.api.model.Destination
import com.devblock.preferences.api.DatastoreRepository


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class SplashViewModel @Inject constructor(
    private val datastore: DatastoreRepository,
    private val navigator: Navigator,
) : BaseViewModel() {


    init {
        viewModelScope.launch {
          //  progressLiveEvent.postValue(true)
            delay(MIN_SPLASH_DURATION)
           // progressLiveEvent.postValue(false)
            if (datastore.isAuthorized()) {
                navigator.goTo(Destination.Welcome)
            } else  navigator.goTo(Destination.Login())


        }
    }

    companion object {
        private const val MIN_SPLASH_DURATION = 3000L
    }
}