package com.devblock.contact.model



sealed class ContactState {
    data class Contact(val name: String, val email: String, val avatar: String): ContactState()
    data class UpdateSuccess(val message: String): ContactState()
    data class UpdateFail(val message: String?): ContactState()


}