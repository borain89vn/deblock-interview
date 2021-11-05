package com.devblock.db.impl.dao

import androidx.room.*
import com.devblock.db.api.models.Profile
import kotlinx.coroutines.flow.Flow

@Dao
internal abstract class ProfileDao {

    @Query(
        """
            SELECT *
            FROM profile
        """
    )
    abstract suspend fun profile(): Profile?

    @Transaction
    open suspend fun updateProfile(profile: Profile){
        deleteProfile()
        insertProfile(profile)
    }

    @Query(
        """
            DELETE FROM profile
        """
    )
    abstract fun deleteProfile()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertProfile(profile: Profile)
}