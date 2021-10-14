package com.devblock.db.api

import com.devblock.db.api.models.Contact
import com.devblock.network.api.response.ContactItemResp
import kotlinx.coroutines.flow.Flow


interface ContactRepository {

    suspend fun addToContact(contact: Contact)
    suspend fun updateContact(firstName: String?, email: String?)
    fun getContacts(): List<Contact>
    fun getContact(id:String): Contact

}