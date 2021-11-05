package com.devblock.login

import android.content.Context
import androidx.lifecycle.*
import com.devblock.base.BaseViewModel
import com.devblock.db.api.ProfileRepository
import com.devblock.db.api.models.Profile
import com.devblock.navigation.Navigator
import com.devblock.navigation.api.model.Destination
import com.devblock.preferences.api.DatastoreRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val datastore: DatastoreRepository,
    private val profileRepository: ProfileRepository,
    private val navigator: Navigator,
) : BaseViewModel() {



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


            if (userName?.isEmpty()){
                errorMessage.value = context.getString(R.string.login_error_username_empty)
                return
            }
            if (password?.isEmpty()){

                errorMessage.value = context.getString(R.string.login_error_password_empty)
                return

            }


         if(userName == "devblock" && password == "2021") {

         }else {
             errorMessage.value = context.getString(R.string.login_error_incorrect)

             return
         }



        viewModelScope.launch(handlerException) {
            progressLiveEvent.value = true

            delay(300)

                datastore.saveUserName(userName)


                profileRepository.updateProfile(
                    Profile(
                       userName = userName
                    )
                )
                progressLiveEvent.value = false

                navigator.goTo(Destination.Welcome)

        }
    }

}