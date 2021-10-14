package com.devblock.db.api

import com.devblock.db.api.models.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun profile(): Profile?
    suspend fun updateProfile(profile: Profile)
    suspend fun deleteProfile()
}