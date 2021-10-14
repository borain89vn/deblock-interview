package com.devblock.welcome


import androidx.lifecycle.*
import com.devblock.BaseViewModel
import com.devblock.db.api.models.Contact
import com.devblock.navigation.Navigator
import com.devblock.network.api.ContactApi
import com.devblock.network.api.response.ContactItemResp

import com.devblock.preferences.api.DatastoreRepository
import com.ezyplanet.thousandhands.util.livedata.NonNullLiveData

import dagger.hilt.android.lifecycle.HiltViewModel
import com.devblock.db.DatabaseConstants
import com.devblock.db.api.ContactRepository
import com.devblock.db.api.ProfileRepository
import com.devblock.navigation.api.model.Destination
import com.devblock.network.api.response.ContactResp
import com.devblock.welcome.model.WelcomeEvent
import com.devblock.welcome.model.WelcomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext


@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val datastore: DatastoreRepository,
    private val navigator: Navigator,
    private val contactRepository: ContactApi,
    private val contactDB: ContactRepository,
    private val profileRepository: ProfileRepository
) : BaseViewModel() {




    private val _state = MutableLiveData<WelcomeState>()
    val state: LiveData<WelcomeState>
        get() = _state

    private val _filterState = MutableLiveData<Boolean>(false)
    val filterState: LiveData<Boolean>
        get() = _filterState

    private var isFilter = false
    var tempContacts : ArrayList<ContactItemResp>? = null


    fun getUsers() {
        viewModelScope.launch {
            _state.value = WelcomeState.Loading
            _state.value = try {

                _state.value = WelcomeState.User(async(Dispatchers.IO){
                    profileRepository?.profile()
                }.await()?.userName)

                val contacts = async (Dispatchers.IO){ contactRepository.getUsers()?.items}
                val localContacts = async(Dispatchers.IO) { contactDB.getContacts()}
                val merge = mergeSource(contacts.await(),localContacts.await())
                if(tempContacts == null){
                    tempContacts = ArrayList(merge)
                }
                WelcomeState.Contacts(merge)
            } catch (e: Exception) {
                WelcomeState.Error(e.localizedMessage)
            }
        }
    }

    fun onItemClicked(item: ContactItemResp) {
        navigator.goTo(Destination.Contact(item.id,item.fullName(), item.email, item.avatar))
    }

    fun filterClick(){
        isFilter = !isFilter
        _filterState.value = isFilter
        if(isFilter) {
            _state.value =
                WelcomeState.Contacts(tempContacts?.filter { it.email == DatabaseConstants.FILTER_EMAIL })
        }else {
            _state.value =
                WelcomeState.Contacts(tempContacts)
        }
    }

    private fun mergeSource(remoteSource:List<ContactItemResp>,localSource:List<Contact>) :List<ContactItemResp>{

        val localMap = localSource.associate { it.id to it }

        return remoteSource.map {
            val contact = localMap[it.id]
            if(contact!= null){
                it.full_name = contact.full_name
                it?.email = contact?.email
            }
            it

        }

    }
    fun refreshData(id:String){
        viewModelScope.launch {
            _state.value = WelcomeState.Loading
            _state.value = try {

                val contact = async(Dispatchers.IO) { contactDB.getContact(id)}
                val merge = mergeSource(ArrayList(tempContacts), mutableListOf(contact.await()))

                    tempContacts = ArrayList(merge)

                WelcomeState.Contacts(merge)
            } catch (e: Exception) {
                WelcomeState.Error(e.localizedMessage)
            }
        }
    }


}

