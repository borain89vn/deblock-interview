package com.devblock.db.impl.repositories


import com.devblock.db.api.ContactRepository
import com.devblock.db.api.models.Contact
import com.devblock.db.impl.dao.ContactDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultContactRepository @Inject constructor(
    private val contactDao: ContactDao,
) : ContactRepository {
    override suspend fun addToContact(contact: Contact) {
        withContext(Dispatchers.IO) {
            contactDao.insertContact(contact)
        }
    }

    override suspend fun updateContact(firstName: String?, email: String?) {
        withContext(Dispatchers.IO) {
            contactDao.update(firstName,email)
        }
    }

    override suspend fun getContacts(): List<Contact>  = contactDao.getContacts()
    override suspend fun getContact(id: String): Contact = contactDao.getContact(id)
}