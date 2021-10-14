package com.devblock.preferences.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "devblock_datastore")

    private val USER_NAME= stringPreferencesKey("user_name")


    private suspend fun <T> save(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { settings ->
            settings[key] = value
        }
    }

    private suspend fun <T> get(key: Preferences.Key<T>): T? =
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[key]
            }
            .first()



    fun authorized(): Flow<Boolean> =
        context.dataStore.data
            .map { preferences ->
                !preferences[USER_NAME].isNullOrEmpty()
            }

    suspend fun isAuthorized(): Boolean = !get(USER_NAME).isNullOrBlank()

    suspend fun saveUserName(userName: String) {
        save(USER_NAME, userName)
    }


}