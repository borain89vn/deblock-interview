package com.devblock.login

import android.content.Context
import androidx.lifecycle.*
import com.devblock.db.api.ProfileRepository
import com.devblock.db.api.models.Profile
import com.devblock.login.model.LoginState
import com.devblock.navigation.Navigator
import com.devblock.navigation.api.model.Destination
import com.devblock.preferences.api.DatastoreRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val datastore: DatastoreRepository,
    private val profileRepository: ProfileRepository,
    private val navigator: Navigator,
) : ViewModel() {

    private val _state = MutableLiveData<LoginState>(LoginState.Idle)
    val state: LiveData<LoginState>
        get() = _state


    val userName: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String?> = MutableLiveData("")
    private val mediator = MediatorLiveData<String>()

    init {
       mediator.addSource(userName){}
        mediator.addSource(password){}
    }


     fun login(
        userName: String,
        password: String
    ) {


            var errorMessage: Int = R.string.empty

            if (userName?.isEmpty()){
                errorMessage = R.string.login_error_username_empty
                _state.value = LoginState.Fail(context.getString(errorMessage))
                return
            }
            if (password?.isEmpty()){
                errorMessage = R.string.login_error_password_empty
                _state.value = LoginState.Fail(context.getString(errorMessage))
                return

            }


         if(userName != "devblock" && password != "2021") {
             errorMessage = R.string.login_error_incorrect
             _state.value = LoginState.Fail(context.getString(errorMessage))
             return
         }



        viewModelScope.launch {
            _state.value = LoginState.Loading
            delay(300)

                datastore.saveUserName(userName)


                profileRepository.updateProfile(
                    Profile(
                       userName = userName
                    )
                )

                _state.value = LoginState.Success
                navigator.goTo(Destination.LoginTarget)

        }
    }

}