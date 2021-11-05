package com.devblock.db.api

import com.devblock.db.api.models.Contact
import com.devblock.network.api.response.ContactItemResp
import kotlinx.coroutines.flow.Flow


interface ContactRepository {

    suspend fun addToContact(contact: Contact)
    suspend fun updateContact(firstName: String?, email: String?)
    suspend fun getContacts(): List<Contact>
    suspend fun getContact(id:String): Contact

}