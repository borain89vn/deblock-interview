package com.devblock.contact

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.devblock.base.BaseViewModel
import com.devblock.db.api.ContactRepository
import com.devblock.db.api.models.Contact
import com.devblock.navigation.Navigator
import com.devblock.utils.AppException
import com.devblock.utils.extensions.isEmailValid
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.util.*

import javax.inject.Inject

@SuppressLint("StaticFieldLeak")


internal class ContactViewModel @Inject constructor(
    private val context: Context,
    private val id: String,
    private val name: String,
    private val email: String,
    private val avatar: String,
    private val navigator: Navigator,
    private val contactRepository: ContactRepository
) : BaseViewModel() {


    val userName: MutableLiveData<String> = MutableLiveData("")
    val userEmail: MutableLiveData<String?> = MutableLiveData("")
    private val mediator = MediatorLiveData<String>()

    init {
        mediator.addSource(userName){}
        mediator.addSource(userEmail){}
        userEmail.value = email
        userName.value = name
        _state.value  = ContactState.Contact(name, email,avatar)
    }


    fun backClick() {
        navigator.back()
    }
    private fun isValidInput():Boolean {

        if (userName.value?.isEmpty() == true|| userEmail.value?.isEmpty() == true) {
            _state.value = ContactState.UpdateFail(context.getString(R.string.contact_update_fail))
            return false
        }
       if(userEmail.value?.isEmailValid()!=true){
           _state.value = ContactState.UpdateFail(context.getString(R.string.auth_field_error_incorrect_email))
           return false
       }
        return true

    }
    fun updateClick() {
        if (!isValidInput()) return

        viewModelScope?.launch(handlerException) {
            val contact = Contact(id,userEmail.value,userName.value,avatar, Date().time)
             contactRepository.addToContact(contact)
            _state.value = ContactState.UpdateSuccess(context.getString(R.string.contact_update_success))
        }


    }

    override fun manageException(exception: Any) {
        super.manageException(exception)
        if(exception is AppException){
            _state.value = ContactState.UpdateFail(exception.message)
        } else if (exception is Throwable){
            _state.value = ContactState.UpdateFail(exception.message)
        }
    }



}

internal class ContactViewModelFactory @AssistedInject constructor(
    @Assisted("id")  private val id: String,
    @Assisted("name")  private val name: String,
    @Assisted("email")  private val email: String,
    @Assisted("avatar")  private val avatar: String,
    @Assisted owner: SavedStateRegistryOwner,
    @Assisted defaultArgs: Bundle? = null,
    @ApplicationContext private val context: Context,
    private val navigator: Navigator,
    private val contactRepository: ContactRepository
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            return ContactViewModel(
                context = context,
                id = id,
                name = name,
                email = email,
                avatar = avatar,
                navigator = navigator,
                contactRepository= contactRepository

            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@AssistedFactory
internal interface ContactViewModelAssistedFactory {
    fun create(
        @Assisted("id") id: String,
        @Assisted("name") name: String,
        @Assisted("email") email: String,
        @Assisted("avatar") avatar: String,
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle? = null,
    ): ContactViewModelFactory
}