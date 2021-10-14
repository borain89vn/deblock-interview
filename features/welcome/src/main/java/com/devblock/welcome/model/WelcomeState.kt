package com.devblock.welcome.model

import com.devblock.network.api.response.ContactItemResp

sealed class WelcomeState {
    object Loading: WelcomeState()
    data class Filter(val isFilter: Boolean): WelcomeState()
    data class User(val userName:String?): WelcomeState()
    data class Contacts(val contacts: List<ContactItemResp>?) : WelcomeState()
    data class Error(val error: String?) : WelcomeState()

    fun toFilter(): Filter? = if(this is Filter) this else null
}