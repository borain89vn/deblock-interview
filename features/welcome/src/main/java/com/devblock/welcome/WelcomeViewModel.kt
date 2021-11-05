package com.devblock.welcome


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.devblock.base.BaseViewModel
import com.devblock.db.api.models.Contact
import com.devblock.navigation.Navigator
import com.devblock.network.api.ContactApi
import com.devblock.network.api.response.ContactItemResp

import dagger.hilt.android.lifecycle.HiltViewModel
import com.devblock.db.DatabaseConstants
import com.devblock.db.api.ContactRepository
import com.devblock.db.api.ProfileRepository
import com.devblock.navigation.api.model.Destination
import com.devblock.utils.paging.ItemComparable
import com.devblock.welcome.model.ContactModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
@SuppressLint("StaticFieldLeak")
class WelcomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val navigator: Navigator,
    private val contactRepository: ContactApi,
    private val contactDB: ContactRepository,
    private val profileRepository: ProfileRepository
) : BaseViewModel() {






    private val _filterState = MutableLiveData<Boolean>(false)
    val filterState: LiveData<Boolean>
        get() = _filterState

    private var isFilter = false
    var tempContacts : ArrayList<ContactItemResp>? = null



    init {
        getUsers()
    }




    fun getUsers() {
        viewModelScope.launch (handlerException){

               progressLiveEvent.value = true

//                  _state.value = WelcomeState.User(async(Dispatchers.IO) {
//                      profileRepository?.profile()
//                  }.await()?.userName)

                  val contacts = async {
                      getFlow()
                  }
//                val localContacts = async { contactDB.getContacts()}
//                val merge = mergeSource(contacts.await(),localContacts.await())
//                if(tempContacts == null){
//                    tempContacts = ArrayList(merge)
//                }
                  // _state.value =  WelcomeState.Contacts(merge)
                  _state.value = WelcomeState.Contacts(contacts.await())
                 progressLiveEvent.value = false



        }
    }

    fun onItemClicked(item: ContactModel) {
        //navigator.goTo(Destination.Contact(item.id,item.fullName(), item.email, item.avatar))
    }

    fun filterClick(){
        isFilter = !isFilter
        _filterState.value = isFilter
//        if(isFilter) {
//            _state.value =
//                WelcomeState.Contacts(tempContacts?.filter { it.email == DatabaseConstants.FILTER_EMAIL })
//        }else {
//            _state.value =
//                WelcomeState.Contacts(tempContacts)
//        }
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
    fun refreshData(id: String) {
        viewModelScope?.launch(handlerException) {


            val contact = contactDB.getContact(id)
            val merge = mergeSource(ArrayList(tempContacts), mutableListOf(contact))


            tempContacts = ArrayList(merge)

          //  _state.value = WelcomeState.Contacts(merge)


        }


    }


    private suspend fun getFlow(): Flow<PagingData<ContactModel>>{

        val flow = contactRepository.getUsers()
     return   flow.map { pagingData: PagingData<ContactItemResp> ->
            pagingData.map { location ->
                ContactModel.Data(location)
            }.insertSeparators<ContactModel.Data, ContactModel> { before, after ->
                when {
                    before == null -> null
                    after == null -> null
                    else -> ContactModel.Seperator("Separator: $before-$after")
                }
            }
        }.cachedIn(viewModelScope)
    }





}

