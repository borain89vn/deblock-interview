package com.devblock.preferences.api

import kotlinx.coroutines.flow.Flow

interface DatastoreRepository {
    fun authorized(): Flow<Boolean>
    suspend fun isAuthorized(): Boolean
    suspend fun saveUserName(userName: String)

}