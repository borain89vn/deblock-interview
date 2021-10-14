package com.devblock.preferences.impl

import com.devblock.preferences.api.DatastoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DefaultDatastoreRepository @Inject constructor(
    private val dataStore: DataStore
): DatastoreRepository {



    override fun authorized(): Flow<Boolean> = dataStore.authorized()

    override suspend fun isAuthorized(): Boolean = dataStore.isAuthorized()

    override suspend fun saveUserName(userName: String) {
        dataStore.saveUserName(userName)
    }
}