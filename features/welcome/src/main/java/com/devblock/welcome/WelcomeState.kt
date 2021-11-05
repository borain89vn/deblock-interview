package com.devblock.welcome

import androidx.paging.PagingData
import com.devblock.base.UIState
import com.devblock.network.api.response.ContactItemResp
import com.devblock.welcome.model.ContactModel
import kotlinx.coroutines.flow.Flow

sealed class WelcomeState: UIState {

    data class User(val userName:String?): WelcomeState()
    data class Contacts(val contacts: Flow<PagingData<ContactModel>>) : WelcomeState()

}