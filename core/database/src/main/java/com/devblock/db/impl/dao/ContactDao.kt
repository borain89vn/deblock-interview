package com.devblock.db.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devblock.db.DatabaseConstants
import com.devblock.db.api.models.Contact

import kotlinx.coroutines.flow.Flow

@Dao
internal interface ContactDao {

    @Query(
        """
            SELECT *
            FROM contacts

            ORDER BY updatedAt
        """
    )
     suspend fun getContacts(): List<Contact>

    @Query(
        """
            SELECT *
            FROM contacts
            WHERE id = :id

            ORDER BY updatedAt
        """
    )
   suspend fun getContact(id:String): Contact



    @Query("UPDATE contacts SET full_name = :first_name WHERE email =:email")
    fun update(first_name: String?, email: String?)



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)
}