package com.devblock.db.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devblock.db.api.models.Contact
import com.devblock.db.api.models.Profile
import com.devblock.db.impl.dao.ContactDao
import com.devblock.db.impl.dao.ProfileDao

@Database(
    entities = [

        Profile::class,
        Contact::class,

    ],
    version = 4,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
    abstract fun profileDao(): ProfileDao
}
