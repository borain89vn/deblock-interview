package com.devblock.db.impl.repositories

import com.devblock.db.api.ProfileRepository
import com.devblock.db.api.models.Profile
import com.devblock.db.impl.dao.ProfileDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultProfileRepository @Inject constructor(
    private val profileDao: ProfileDao
) : ProfileRepository {

    override suspend fun profile(): Profile? = profileDao?.profile()

    override suspend fun updateProfile(profile: Profile) =
        withContext(Dispatchers.IO) {
            profileDao.updateProfile(profile)
        }

    override suspend fun deleteProfile() =
        withContext(Dispatchers.IO){
            profileDao.deleteProfile()
        }
}