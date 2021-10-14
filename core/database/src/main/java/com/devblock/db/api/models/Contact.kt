package com.devblock.db.api.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey
    val id: String,
    val email: String?,
    val full_name: String?,
    val avatar: String?,
    val updatedAt: Long?
)